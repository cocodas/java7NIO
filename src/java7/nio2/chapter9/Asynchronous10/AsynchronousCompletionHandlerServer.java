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
				
				System.out.println("���� ���Դϴ� ��ٷ� �ֽʽÿ�.....");
				
				asynchronousServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
					
					final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

					@Override
					public void completed(AsynchronousSocketChannel result, Void attachment) {
						asynchronousServerSocketChannel.accept(null, this);
						
						try {
							System.out.println("[ " + result.getRemoteAddress() + " ] �κ��� ���� �Ǿ����ϴ�.");
							
							
							//������ ����
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
						throw new UnsupportedOperationException("������ �޾� ���ϼ� �����ϴ�!!!");
					}
				});
				
				//��� �ϴ� �κ�
				System.in.read();
			}else {
				System.out.println("�񵿱� ���� ���� ä���� ���� �����ϴ�.!!");
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}
