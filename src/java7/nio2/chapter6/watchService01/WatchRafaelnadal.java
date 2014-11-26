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
			
			//무한 루프를 시작
			while (true) {
				//와치키를 가져오고 큐에서 제거 poll(), poll(long, TimeUnit()도 가능
				//poll() : 이용 할수 있는 키가 없으면 즉시 널 값을 반환 한다.
				//poll(long, TimeUnit() : 이용할수 있는 키가 없으면 지정된 시간동안 대기한 다음 다시 시도 한다. 다시 시도 해도 이용할수 있는 키가 없으면 널 반환
				final WatchKey key = watchService.take();
				
				//해당 와치 키의 미처리 이벤트 리스트를 가져온다.
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					
					//이벤트 종류를 가져온다(생성, 수정, 삭제)
					final Kind<?> kind = watchEvent.kind();
					
					//OVERFLOW를 처리 한다
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}
					
					//해당 이벤트의 파일 이름을 가져온다.
					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
					final Path fileName = watchEventPath.context();
					
					//결과를 출력
					System.out.println(kind + " ---> " + fileName );
					
				}
				
				//키를 초기화 한다
				boolean valid = key.reset();
				
				//예를 들어 디렉터리가 삭제된 경우와 같이 키가 더이상 유효 하지 않다면 루프는 종료한다.
				if (!valid) {
					break;
				}					
			}					
		}
	}
}
