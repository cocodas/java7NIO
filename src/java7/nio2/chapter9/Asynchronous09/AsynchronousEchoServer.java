package java7.nio2.chapter9.Asynchronous09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.HostnameVerifier;

public class AsynchronousEchoServer {
	
	final int DEFAUALT_PORT = 5555;
	final String IP = "192.168.85.1";
	
	public void futureServer() {
		
		//�⺻ �׷쿡 ���ε��� �񵿱� ���� ���� ä���� �����Ѵ�.
		try {
			AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			
			//���� �ƴ��� Ȯ��
			if (asynchronousServerSocketChannel.isOpen()) {
				
				//�� ���� �ɼ� ���� ���� ���� ������ �⺻���� ����
				asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
				asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				
				//�񵿱� ���� ���� ä���� ���� �ּҿ� ���ε�
				asynchronousServerSocketChannel.bind(new InetSocketAddress(IP, DEFAUALT_PORT));
				
				//Ŭ���̾�Ʈ�� ��ٸ��ٴ� ��� �޼��� ǥ��
				System.out.println("������ ��ٸ��� �ֽ��ϴ�....");
				
				//Ŭ���̾�Ʈ ���� ���� ����
				while (true) {
					Future<AsynchronousSocketChannel> asynchronousSocketChannelFuture = asynchronousServerSocketChannel.accept();
					
					try {
						final AsynchronousSocketChannel asynchronousSocketChannel = asynchronousSocketChannelFuture.get();

						System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ] �κ��� ���� �Ǿ����ϴ�.");
						
						final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
						
						while (asynchronousSocketChannel.read(buffer).get() != -1) {
							
							buffer.flip();
							
							asynchronousSocketChannel.write(buffer).get();
							//System.out.println(buffer);
							
							if (buffer.hasRemaining()) {
								buffer.compact();
							} else {
								buffer.clear();
							}
						}
						
						System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ] �� ���������� �ǵ��� �־����ϴ�!" );
						
					} catch (IOException | InterruptedException | ExecutionException e) {
						System.err.println(e);
					} 
				}
			}else {
				System.out.println("�񵿱� ���� ���� ä���� ������ �ʾҽ��ϴ�!!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
