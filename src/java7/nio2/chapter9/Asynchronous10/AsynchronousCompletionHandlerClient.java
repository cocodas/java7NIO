package java7.nio2.chapter9.Asynchronous10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AsynchronousCompletionHandlerClient extends ClientBasicSetting{
	
	public void completionHandlerClient() {
		
		try {
			final AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
			
			if (asynchronousSocketChannel.isOpen()) {
				
				asynchronousSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
				asynchronousSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
				asynchronousSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
				
				//이 채널의 소켓을 연결
				asynchronousSocketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT), null, new CompletionHandler<Void, Void>() {

					
					
					@Override
					public void completed(Void result, Void attachment) {
						try {
							
							System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ]에 성공적으로 연결 되었습니다!");

							//데이터 전송
							asynchronousSocketChannel.write(helloBuffer).get();
							
							while (asynchronousSocketChannel.read(buffer).get() != -1) {
								buffer.flip();
								
								charBuffer = decoder.decode(buffer);
								System.out.println(charBuffer.toString());
								
								if (buffer.hasRemaining()) {
									buffer.compact();
								}else {
									buffer.clear();
								}
								
								int r = new Random().nextInt(100);
								if (r == 50) {
									System.out.println("50에 도달하였습니다. 비동기 소켓 채널을 닫습니다.!");
									break;
								}else {
									
									randomBuffer = ByteBuffer.wrap("램돔 숫자 : ".concat(String.valueOf(r)).getBytes());
									asynchronousSocketChannel.write(randomBuffer).get();
								}
							}
						} catch (IOException | InterruptedException | ExecutionException ex) {
							System.err.println(ex);
						}finally{
							try {
								asynchronousSocketChannel.close();
							} catch (IOException ex) {
								System.err.println(ex);
							}
						}
					}

					@Override
					public void failed(Throwable exc, Void attachment) {
						throw new UnsupportedOperationException("연결은 성립되 않았습니다!!");
					}
				});
				
				System.in.read();
				
			}else {
				System.out.println("비동기 소켓 채널이 열려지 않았습니다!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
