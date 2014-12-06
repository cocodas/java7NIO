package java7.nio2.chapter9.Asynchronous07;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousCompletionHandlerLock {
	//completionHandler() 형태의 Lock()메서드 구현
	
	static Thread current;
	
	public void completinHandlerLock(Path path) {
				
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
			
			current = Thread.currentThread();
			
			asynchronousFileChannel.lock("잠금 상태 : ", new CompletionHandler<FileLock, Object>() {

				@Override
				public void completed(FileLock result, Object attachment) {
					System.out.println(attachment + "[ " + result.isValid() + " ]");
					
					if (result.isValid()) {
						System.out.println("잠겨 있는 파일입니다!!");
						
						try {
							result.release();
						} catch (IOException e) {
							System.err.println(e);
						}
					}
					current.interrupt();
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					System.out.println(attachment);
					System.out.println("Erro : " + exc);
					current.interrupt();
				}
			});
			
			System.out.println("Waiting for file to be locked and pocess...\n");
			
			try {
				current.join();
			} catch (InterruptedException e) {
			}
			System.out.println("\n\n모든 것이 닫혔습니다 바이바이바이....");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
