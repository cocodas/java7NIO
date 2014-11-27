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
		
		//디렉터리를 Kind<Path> 이벤트에 대해 watchService와 함께 등록한다.
		path.register(watchService, kind);
	}
	
	public void watchVideoCamera(Path path) throws IOException, InterruptedException {
		watchService = FileSystems.getDefault().newWatchService();
		register(path, StandardWatchEventKinds.ENTRY_CREATE);
		
		//무한루프 시작
		OUTERMOST:
		while (true) {
			//와치 키를 가져오고 큐에서 제거 한다 11초동안 대기
			final WatchKey key = watchService.poll(11, TimeUnit.SECONDS);
			
			if (key == null) {
				System.out.println("비디오 카메라가 혼잡한 상황!! security System이 취소 되었습니다!");
				break;
			}else {
				//해당 와치키의 이벤트 목록을 가져온다.
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					
					//이벤트 종류를 알아 낸다.(생성, 수정, 삭제)
					final Kind<?> kind = watchEvent.kind();
					
					//OVERFLOW 해결
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;//무시
					}
					
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						
						//이벤트에 대한 파일 이름을 알아낸다.
						final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
						final Path fileName = watchEventPath.context();
						final Path child = path.resolve(fileName);
						
//						System.out.println("kind : " + kind);
//						System.out.println("watchEventPath : " + watchEventPath);
//						System.out.println("fileName : " + fileName);
//						System.out.println("child : " + child);
						
						if (Files.probeContentType(child).equals("image/jpeg")) {
							
							//비디오 화면 저장시간 출력한다.
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
							System.out.println(dateFormat.format(new Date()) + "에 성공적으로 Video Capture 되었습니다!");							
						}else {
							System.out.println("Viideo Capture에 실패 하였습니다! 바이러스가 걸렸을수도 있습니다!");
							break OUTERMOST;
						}
					}
				}
				
				//키를 초기화 한다.
				boolean valid = key.reset();
				
				//키가 유효 하지 않으면 루프를 빠져 나온다.
				if (!valid) {
					break;
				}
			}			
		}
		
		watchService.close();
	}
}
