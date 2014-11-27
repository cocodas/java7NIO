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
			
			//무한루프 시작
			while (true) {
				
				//와치 키를 큐에서 제거
				final WatchKey key = watchService.poll(10, TimeUnit.SECONDS);
				
				//해당 와치키의 이벤트 목록 가져오기
				if (key != null) {
					for (WatchEvent<?> watchEvent : key.pollEvents()) {
						
						//해당 이벤트의 파일 이름을 알아낸다.
						final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
						final Path fileName = watchEventPath.context();
						
						//이벤트의 종류를 알아낸다.(생성 삭제 수정)
						final Kind<?> kind = watchEvent.kind();
						
						//OVERFLOW 처리
						if (kind == StandardWatchEventKinds.OVERFLOW) {
							continue;
						}
						
						if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
							System.out.println("문서를 프린터로 보내는 중..... " + "[ " + fileName + " ]");
							
							Runnable task = new Print(path.resolve(fileName));
							Thread worker = new Thread(task);
							
							//스레드의 이름을 설정한다.
							worker.setName(path.resolve(fileName).toString());
							
							//스레드의 경로를 저장한다.
							threads.put(worker, path.resolve(fileName));
							
							//스레드를 시작한다. run() 메서드를 직접 호출하지 않는다.
							worker.start();							
						}
						
						if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
							System.out.println("[ " + fileName + " ]" + " 성공적으로 프린터 되었습니다!!");
						}
					}
					
					//키를 초기화 한다.
					boolean valid = key.reset();
					
					//키가 유효 하지 않으면 루프를 빠져 나온다.
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
