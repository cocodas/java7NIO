package java7.nio2.chapter6.watchService04;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WatchPrinterTray {
	
	private final Map<Thread, Path> threads = new HashMap<>();
	
	public void watchTray(Path path) throws IOException, InterruptedException {
		
		try (WatchService watchService = FileSystems.getDefault().newWatchService()){
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
			
			//���ѷ��� ����
			while (true) {
				
				//��ġ Ű�� ť���� ����
				final WatchKey key = watchService.poll(10, TimeUnit.SECONDS);
				
				//�ش� ��ġŰ�� �̺�Ʈ ��� ��������
				if (key != null) {
					for (WatchEvent<?> watchEvent : key.pollEvents()) {
						
						//�ش� �̺�Ʈ�� ���� �̸��� �˾Ƴ���.
						final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
						final Path fileName = watchEventPath.context();
						
						//�̺�Ʈ�� ������ �˾Ƴ���.(���� ���� ����)
						final Kind<?> kind = watchEvent.kind();
						
						//OVERFLOW ó��
						if (kind == StandardWatchEventKinds.OVERFLOW) {
							continue;
						}
						
						if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
							System.out.println("������ �����ͷ� ������ ��..... " + "[ " + fileName + " ]");
							
							Runnable task = new Print(path.resolve(fileName));
							Thread worker = new Thread(task);
							
							//�������� �̸��� �����Ѵ�.
							worker.setName(path.resolve(fileName).toString());
							
							//�������� ��θ� �����Ѵ�.
							threads.put(worker, path.resolve(fileName));
							
							//�����带 �����Ѵ�. run() �޼��带 ���� ȣ������ �ʴ´�.
							worker.start();							
						}
						
						if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
							System.out.println("[ " + fileName + " ]" + " ���������� ������ �Ǿ����ϴ�!!");
						}
					}
					
					//Ű�� �ʱ�ȭ �Ѵ�.
					boolean valid = key.reset();
					
					//Ű�� ��ȿ ���� ������ ������ ���� ���´�.
					if (!valid) {
						threads.clear();
						break;
					}
				}
				
				if (!threads.isEmpty()) {
					for (Iterator<Map.Entry<Thread, Path>> iterator = threads.entrySet().iterator(); iterator.hasNext();) {
						
						Map.Entry<Thread, Path> entry = iterator.next();
						if (entry.getKey().getState() == Thread.State.TERMINATED) {
							Files.deleteIfExists(entry.getValue());
							iterator.remove();
						}	
					}			
				}
			}
		}		
	}
}
