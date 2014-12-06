package java7.nio2.chapter9.Asynchronous04;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsynchronousCompletionHandler {
	
	//파일 읽기와 completionHandler
	
	static Thread current;
	ByteBuffer buffer = ByteBuffer.allocate(10000);
	
	public void completionHander(Path path) {
	
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			
			current = Thread.currentThread();
			asynchronousFileChannel.read(buffer, 0, "읽기 작업 중...", new CompletionHandler<Integer, Object>(){

				@Override
				public void completed(Integer result, Object attachment) {
					System.out.println(attachment);
					System.out.print("Read bytes : " + "[ " + result + " ]");
					current.interrupt();
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					System.out.println(attachment);
					System.out.println("Error : " + "[ " + exc + " ]");
					current.interrupt();
				}
			});
			
			System.out.println("\n읽기 작업이 끝날때 까지 기다려 주십시오...\n");
			try {
				current.join();
			} catch (InterruptedException e) {
			}
			
			//이제 버퍼에는 읽어 들인 바이트가 들어 있다.
			System.out.println("\n\n모든 것들이 닫혔다 바이바이...");

		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
