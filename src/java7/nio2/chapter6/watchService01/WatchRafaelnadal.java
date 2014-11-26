package java7.nio2.chapter6.watchService01;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchRafaelnadal {
	
	public void watchRNDir(Path path) throws IOException, InterruptedException {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()){
			
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
			
			//���� ������ ����
			while (true) {
				//��ġŰ�� �������� ť���� ���� poll(), poll(long, TimeUnit()�� ����
				//poll() : �̿� �Ҽ� �ִ� Ű�� ������ ��� �� ���� ��ȯ �Ѵ�.
				//poll(long, TimeUnit() : �̿��Ҽ� �ִ� Ű�� ������ ������ �ð����� ����� ���� �ٽ� �õ� �Ѵ�. �ٽ� �õ� �ص� �̿��Ҽ� �ִ� Ű�� ������ �� ��ȯ
				final WatchKey key = watchService.take();
				
				//�ش� ��ġ Ű�� ��ó�� �̺�Ʈ ����Ʈ�� �����´�.
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					
					//�̺�Ʈ ������ �����´�(����, ����, ����)
					final Kind<?> kind = watchEvent.kind();
					
					//OVERFLOW�� ó�� �Ѵ�
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}
					
					//�ش� �̺�Ʈ�� ���� �̸��� �����´�.
					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
					final Path fileName = watchEventPath.context();
					
					//����� ���
					System.out.println(kind + " ---> " + fileName );
					
				}
				
				//Ű�� �ʱ�ȭ �Ѵ�
				boolean valid = key.reset();
				
				//���� ��� ���͸��� ������ ���� ���� Ű�� ���̻� ��ȿ ���� �ʴٸ� ������ �����Ѵ�.
				if (!valid) {
					break;
				}					
			}					
		}
	}
}
