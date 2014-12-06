package java7.nio2.chapter9.Asynchronous06;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousLock {
	
	ByteBuffer buffer = ByteBuffer.wrap("\nThe Java memory model describes the communication between the memory of the threads and the main memory of the application. It defines the rules how changes in the memory done by threads are propagated to other threads. The Java memory model also defines the situations in which a thread re-fresh its own memory from the main memory.".getBytes());
	
	public void AsynchronousLock(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
			
			Future<FileLock> futureLock = asynchronousFileChannel.lock();
			
			System.out.println("파일을 잠그기 위해서 잠시만 기다려주세요...");
			
			FileLock lock = futureLock.get();
			//축약 형태로도 사용 가능
			//FileLock lock = asynchronousFileChannel.lock().get();
			
			if (lock.isValid()) {
				Future<Integer> futureWrite = asynchronousFileChannel.write(buffer, 4500);
				System.out.println("쓰기가 완료 될때까지 기다려주세요....");
				int written = futureWrite.get();
				//축약해서 사용 가능
				//int written = asynchronousFileChannel.write(buffer, 4500).get();
				
				System.out.println("나는 " + "[ " + written + " ]" + "bytes를 " + "[ " + path.getFileName() + " ]" + " 파일에 작성 하였습니다" );
				
				lock.release();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	} 
	
	
	

}
