package java7.nio2.chapter7.fileChannel02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class FileLockSystem {
	
	ByteBuffer buffer = ByteBuffer.wrap("Welcome to cocodas !!".getBytes());
	
	public void fileLock(Path path) {
		try {
			FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE));
			
			//파일에 잠금을 만들기 위해 파일 채널을 사용한다.
			//이 메서드는 잠금을 가져올 때까지 차단한다.
			FileLock lock = fileChannel.lock();
			
			//차단(blocking) 없이 잠금을 획득하려 한다.
			//이 메서드는 파일이 이미 잠겨 있다면 null이나 예외를 발생한다.
			
//			try {
//				lock = fileChannel.tryLock();
//				
//			} catch (OverlappingFileLockException e) {
//				System.err.println(e);
//			이 스레드나 가상머신에서 파일이 이미 잠겨 있다.
//			}
			
			if (lock.isValid()) {
				System.out.println("@@@ 쓰기가 잠겨있는 파일 입니다!! @@@");
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					System.err.println(e);
				}
				
				fileChannel.position(0);
				fileChannel.write(buffer);
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}
			
			//잠금을 해제 한다
			lock.release();
			
			System.out.println("\n잠금이 해제 되었습니다!!!");

		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
	
	

}
