package java7.nio2.chapter9.Asynchronous09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsynchronousExecutorEchoServer {
	final int DEFAUALT_PORT = 5555;
	final String IP = "192.168.85.1";
	ExecutorService taskExecutor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
	
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
						
						Callable<String> worker = new Callable<String>() {

							@Override
							public String call() throws Exception {
								String host = asynchronousSocketChannel.getRemoteAddress().toString();
								System.out.println("[ " + host + " ] �κ��� ���� �Ǿ����ϴ�.");
								//System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ] �κ��� ���� �Ǿ����ϴ�.");
								
								final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
								
								//������ ����
								while (asynchronousSocketChannel.read(buffer).get() != -1) {
									
									buffer.flip();
									
									asynchronousSocketChannel.write(buffer).get();
									
									if (buffer.hasRemaining()) {
										buffer.compact();
									} else {
										buffer.clear();
									}
								}
								
								System.out.println("1");
								asynchronousSocketChannel.close();
								System.out.println("[ " + host + " ] �� ���������� �ǵ��� �־����ϴ�!" );
								//System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ] �� ���������� �ǵ��� �־����ϴ�!" );
								return host;
							}
						};
						
						taskExecutor.submit(worker);
						
					} catch (InterruptedException | ExecutionException e) {
						System.err.println(e);
						
						//�ͽ�ť�Ͱ� �� �����带 �������� ���ϰ� �ϰ�
						//ť�� �ִ� ��� �����带 ������.
						System.err.println("\n ������ ������ �������ϴ�.");

						//��� �����尡 ���� ������ ����Ѵ�.
						while (!taskExecutor.isTerminated()) {
						}
						
						break;
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
