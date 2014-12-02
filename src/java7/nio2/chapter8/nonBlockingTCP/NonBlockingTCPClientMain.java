package java7.nio2.chapter8.nonBlockingTCP;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class NonBlockingTCPClientMain {

	public static void main(String[] args) {
		final int DEFAULT_PORT = 5555;
		final String IP = "127.0.0.1";
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(2*1024);
		ByteBuffer randomBuffer;
		CharBuffer charBuffer;
		
		Charset charset = Charset.defaultCharset();
		CharsetDecoder decoder = charset.newDecoder();
		
		//open()�޼��带 ȣ���ؼ� Selector�� ServerSocketChannel�� ����.
		try {
			Selector selector = Selector.open();
			SocketChannel socketChannel = SocketChannel.open();
			
			//�����;� ��������ä���� ���������� ���ȴ��� Ȯ�� �Ѵ�.
			if ((socketChannel.isOpen()) && (selector.isOpen())) {
				
				//���ŷ ��带 �����Ѵ�
				socketChannel.configureBlocking(false);
				
				//��� �ɼ��� �����Ѵ�.
				socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
				
				//������ ä���� �־��� �����Ϳ� ��� �Ѵ�.
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
				
				//������ ȣ��Ʈ�� �����Ѵ�.
				socketChannel.connect(new java.net.InetSocketAddress(IP, DEFAULT_PORT));
				
				System.out.println("Localhost : " + socketChannel.getLocalAddress());
				
				//������ ��ٸ���.
				while (selector.select(1000) > 0) {
					
					//Ű�� �����´�.
					Set keys = selector.selectedKeys();
					Iterator its = keys.iterator();
					
					//��Ű�� ó�� �Ѵ�.
					while (its.hasNext()) {
						
						SelectionKey key = (SelectionKey) its.next();
						
						//���� Ű�� �����Ѵ�.
						its.remove();
						
						//�� Ű�� ���� ���� ä���� �����´�.
						try {
							SocketChannel keySocketChannel = (SocketChannel) key.channel();
							
							//���� �õ�
							if (key.isConnectable()) {
								//���� ���� �޼��� �߼�
								System.out.println("���� �Ǿ����ϴ�!!!");
								
								//��ó�� ������ �ݴ´�.
								if (keySocketChannel.isConnectionPending()) {
									keySocketChannel.finishConnect();
								}
								
								//������ �б�, ������ ���⸦ ó���Ѵ�.
								while (keySocketChannel.read(buffer) != -1) {
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
										System.out.println("50�� ���Խ��ϴ�. socket channel�� �ݰڽ��ϴ�!!");
										socketChannel.close();
										break;
									} else {
										randomBuffer = ByteBuffer.wrap("������ �� : ".concat(String.valueOf(r)).getBytes("UTF-8"));
										keySocketChannel.write(randomBuffer);
										try {
											Thread.sleep(1500);
											
										} catch (InterruptedException e) {
										}
									}		
								}
							}
						} catch (IOException e) {
							System.err.println(e);
						}
					}
				}
				
			}else {
				System.out.println("socket channel�̳� selector�� �������� �ʽ��ϴ�.!");
			}	
		} catch (IOException e) {
			System.err.println(e);
		}
	}	
}
