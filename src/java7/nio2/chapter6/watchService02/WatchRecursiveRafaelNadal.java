package java7.nio2.chapter6.watchService02;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class WatchRecursiveRafaelNadal{
	private WatchService watchService;
	private final Map<WatchKey, Path> directories = new HashMap<>();
	//HashMap : ���� ����
	
	private void registerPath(Path path) throws IOException{
		//���� ��θ� ����Ѵ�.
		WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		
		//Ű�� ��θ� �����Ѵ�.
		directories.put(key, path);
	}
	
	private void registerTree(Path start)  throws IOException {
		Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
			//4���� FileVisitor �޼��尡 �� �ʿ� ������ SimpleFileVisitor Ŭ������ �̿� �Ѵ�. ���ϴ� �޼��常 �������̵� ����
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("Registering : " + dir);
				registerPath(dir);
				return FileVisitResult.CONTINUE;
			}					
		});
	}
	
	public void watchRNDir(Path start) throws IOException, InterruptedException {
		
		//watchService ����
		watchService = FileSystems.getDefault().newWatchService();
		
		registerTree(start);
		
		//���ѷ��� ����
		while (true) {
			//��ġŰ�� �������� ť���� ����
			final WatchKey key = watchService.take();
			
			//�ش� ��ġŰ�� ���� �̺�Ʈ ����� �����´�.
			for (WatchEvent<?> watchEvent : key.pollEvents()) {
				//�̺�Ʈ ���� �˾Ƴ���(����, ����, ����) Modifier���� ���̽��� �ִ�
				final Kind<?> kind = watchEvent.kind();
				
				//�ش� �̺�Ʈ�� ���� ���� �̸��� �˾Ƴ���.
				final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
				final Path fileName = watchEventPath.context();
				
				//OVERFLOW �̺�Ʈ ó��
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}
				
				//CREATE �̺�Ʈ ó��
				if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
					final Path directoryPath = directories.get(key);
					final Path child = directoryPath.resolve(fileName);
					
					if (Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {
						registerTree(child);
					}
				}
				
				System.out.println(kind + " ---> " + fileName);
			}
			
			//Ű�� �ʱ�ȭ
			boolean valid = key.reset();
			
			if (!valid) {
				directories.remove(key);
				if (directories.isEmpty()) {
					break;
				}
			}		
		}
		watchService.close();	
	}

}
