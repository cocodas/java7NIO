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
				
				//�� ä���� ������ ����
				asynchronousSocketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT), null, new CompletionHandler<Void, Void>() {

					
					
					@Override
					public void completed(Void result, Void attachment) {
						try {
							
							System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ]�� ���������� ���� �Ǿ����ϴ�!");

							//������ ����
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
									System.out.println("50�� �����Ͽ����ϴ�. �񵿱� ���� ä���� �ݽ��ϴ�.!");
									break;
								}else {
									
									randomBuffer = ByteBuffer.wrap("���� ���� : ".concat(String.valueOf(r)).getBytes());
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
						throw new UnsupportedOperationException("������ ������ �ʾҽ��ϴ�!!");
					}
				});
				
				System.in.read();
				
			}else {
				System.out.println("�񵿱� ���� ä���� ������ �ʾҽ��ϴ�!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
