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
	//HashMap : 예제 참고
	
	private void registerPath(Path path) throws IOException{
		//받은 경로를 등록한다.
		WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		
		//키와 경로를 저장한다.
		directories.put(key, path);
	}
	
	private void registerTree(Path start)  throws IOException {
		Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
			//4가지 FileVisitor 메서드가 다 필요 없을때 SimpleFileVisitor 클래스를 이용 한다. 원하는 메서드만 오버라이드 가능
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("Registering : " + dir);
				registerPath(dir);
				return FileVisitResult.CONTINUE;
			}					
		});
	}
	
	public void watchRNDir(Path start) throws IOException, InterruptedException {
		
		//watchService 생성
		watchService = FileSystems.getDefault().newWatchService();
		
		registerTree(start);
		
		//무한루프 시작
		while (true) {
			//와치키를 가져오고 큐에서 제거
			final WatchKey key = watchService.take();
			
			//해당 와치키에 대한 이벤트 목록을 가져온다.
			for (WatchEvent<?> watchEvent : key.pollEvents()) {
				//이벤트 종류 알아낸다(수정, 생성, 삭제) Modifier인터 페이스도 있다
				final Kind<?> kind = watchEvent.kind();
				
				//해당 이벤트에 대한 파일 이름을 알아낸다.
				final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
				final Path fileName = watchEventPath.context();
				
				//OVERFLOW 이벤트 처리
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}
				
				//CREATE 이벤트 처리
				if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
					final Path directoryPath = directories.get(key);
					final Path child = directoryPath.resolve(fileName);
					
					if (Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {
						registerTree(child);
					}
				}
				
				System.out.println(kind + " ---> " + fileName);
			}
			
			//키를 초기화
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
