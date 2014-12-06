package java7.nio2.chapter9.Asynchronous10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AsynchronousCompletionHandlerServer extends ServerBasicSetting{
	
	public void completionHandlerServer() {
		
		try {
			final AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			
			if (asynchronousServerSocketChannel.isOpen()) {
				asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
				asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				
				asynchronousServerSocketChannel.bind(new InetSocketAddress(IP, DAFAULT_PORT));
				
				System.out.println("연결 중입니다 기다려 주십시요.....");
				
				asynchronousServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
					
					final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

					@Override
					public void completed(AsynchronousSocketChannel result, Void attachment) {
						asynchronousServerSocketChannel.accept(null, this);
						
						try {
							System.out.println("[ " + result.getRemoteAddress() + " ] 로부터 연결 되었습니다.");
							
							
							//데이터 전송
							while (result.read(buffer).get() != -1) {
								buffer.flip();
								
								result.write(buffer).get();
								
								if (buffer.hasRemaining()) {
									buffer.compact();
								}else {
									buffer.clear();
								}
							}
							
						} catch (IOException | InterruptedException | ExecutionException ex) {
							System.err.println(ex);
						} finally{
							
							try {
								result.close();
							} catch (IOException e) {
								System.err.println(e);
							}
						}
					}

					@Override
					public void failed(Throwable exc, Void attachment) {
						asynchronousServerSocketChannel.accept(null, this);
						throw new UnsupportedOperationException("연결을 받아 들일수 없습니다!!!");
					}
				});
				
				//대기 하는 부분
				System.in.read();
			}else {
				System.out.println("비동기 서버 소켓 채널을 열수 없습니다.!!");
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}
