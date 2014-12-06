package java7.nio2.chapter9.Asynchronous05;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsynchronousCompletionHandler {
	
	//complet() 메서드 안에서 첨부된 객체를 해독(decode) 하고 대상 ByteBuffer 내용을 표시한다. 
	
	static Thread current;
	
	CompletionHandler<Integer, ByteBuffer> handler = new CompletionHandler<Integer, ByteBuffer>() {
		
		String encoding = System.getProperty("file.encoding");
		
		@Override
		public void completed(Integer result, ByteBuffer attachment) {
			System.out.println("Read bytes : " + "[ " + result + " ]");
			attachment.flip();
			System.out.print(Charset.forName(encoding).decode(attachment));			
			attachment.clear();
			current.interrupt();
		}
		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			System.out.println(attachment);
			System.out.println("Error : " + exc);
			current.interrupt();
		}
	};
	
	public void completionHandler(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			
			current = Thread.currentThread();
			ByteBuffer buffer = ByteBuffer.allocate(10000);
			asynchronousFileChannel.read(buffer, 0, buffer, handler);
			
			System.out.println("읽기 작업이 끝날때 까지 기다려 주십시오....");
			
			try {
				current.join();
			} catch (InterruptedException e) {
			}
			
			System.out.println("모든 것들이 닫혔습니다 바이바이....");
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	

}
