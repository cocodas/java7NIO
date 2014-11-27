package java7.nio2.chapter6.watchService03;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SecurityWatch {
	
	WatchService watchService;
	
	private void register(Path path, Kind<Path> kind) throws IOException{
		
		//���͸��� Kind<Path> �̺�Ʈ�� ���� watchService�� �Բ� ����Ѵ�.
		path.register(watchService, kind);
	}
	
	public void watchVideoCamera(Path path) throws IOException, InterruptedException {
		watchService = FileSystems.getDefault().newWatchService();
		register(path, StandardWatchEventKinds.ENTRY_CREATE);
		
		//���ѷ��� ����
		OUTERMOST:
		while (true) {
			//��ġ Ű�� �������� ť���� ���� �Ѵ� 11�ʵ��� ���
			final WatchKey key = watchService.poll(11, TimeUnit.SECONDS);
			
			if (key == null) {
				System.out.println("���� ī�޶� ȥ���� ��Ȳ!! security System�� ��� �Ǿ����ϴ�!");
				break;
			}else {
				//�ش� ��ġŰ�� �̺�Ʈ ����� �����´�.
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					
					//�̺�Ʈ ������ �˾� ����.(����, ����, ����)
					final Kind<?> kind = watchEvent.kind();
					
					//OVERFLOW �ذ�
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;//����
					}
					
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						
						//�̺�Ʈ�� ���� ���� �̸��� �˾Ƴ���.
						final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
						final Path fileName = watchEventPath.context();
						final Path child = path.resolve(fileName);
						
//						System.out.println("kind : " + kind);
//						System.out.println("watchEventPath : " + watchEventPath);
//						System.out.println("fileName : " + fileName);
//						System.out.println("child : " + child);
						
						if (Files.probeContentType(child).equals("image/jpeg")) {
							
							//���� ȭ�� ����ð� ����Ѵ�.
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
							System.out.println(dateFormat.format(new Date()) + "�� ���������� Video Capture �Ǿ����ϴ�!");							
						}else {
							System.out.println("Viideo Capture�� ���� �Ͽ����ϴ�! ���̷����� �ɷ������� �ֽ��ϴ�!");
							break OUTERMOST;
						}
					}
				}
				
				//Ű�� �ʱ�ȭ �Ѵ�.
				boolean valid = key.reset();
				
				//Ű�� ��ȿ ���� ������ ������ ���� ���´�.
				if (!valid) {
					break;
				}
			}			
		}
		
		watchService.close();
	}
}
