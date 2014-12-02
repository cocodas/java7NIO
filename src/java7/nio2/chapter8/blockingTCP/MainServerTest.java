package java7.nio2.chapter8.blockingTCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MainServerTest {

	public static void main(String[] args) {
		final int DEFAULT_PORT = 5555;
		final String IP = "127.0.0.1";
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
			try {
				//���ο� ���� ���� ä���� �����Ѵ�.
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				
				//���� ���� ä�� ������ ���������� �۾��� ����Ѵ�.
				if (serverSocketChannel.isOpen()) {
					
					//���ŷ ��带 �����Ѵ�.
					serverSocketChannel.configureBlocking(true);
					//��� �ɼ��� �����Ѵ�.
					serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
					serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
					//���� ���� ä���� ���� �ּҷ� ���ε��Ѵ�.
					serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
					
					//Ŭ���̾�Ʈ�� ��ٸ��µ��� ��� �޼����� ǥ�� �Ѵ�.
					System.out.println("���� ���Դϴ�. ��ٷ� �ֽʽÿ�....");
					
					//���� ������ ��ٸ���.
					while (true) {
						try {
							SocketChannel socketChannel = serverSocketChannel.accept();
							
							System.out.println(socketChannel.getRemoteAddress() + "�� ���� ���� �Ǿ����ϴ�. ");
							
							//������ ����
							while (socketChannel.read(buffer) != -1) {
								buffer.flip();
								socketChannel.write(buffer);
								
								if (buffer.hasRemaining()) {
									buffer.compact();
								}else {
									buffer.clear();
								}
								
							}
							
						} catch (IOException e) {
						}
					}					
				}else {
					System.out.println("server socket channel�� ���� �����ϴ�!!");
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}

}
