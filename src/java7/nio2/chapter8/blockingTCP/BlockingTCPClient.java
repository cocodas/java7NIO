package java7.nio2.chapter8.blockingTCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Random;

public class BlockingTCPClient extends BasicSetting{
	
	//�� ����ä�� �����Ѵ�.
	public void clientSocket() {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			
			//���� ä�� ������ ���������� ��� ����
			if (socketChannel.isOpen()) {
				//���ŷ ��� ����
				socketChannel.configureBlocking(true);
				//�ɼ� ����
				socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				socketChannel.setOption(StandardSocketOptions.SO_LINGER, 5);
				
				//�� ä���� ���Ͽ� ����
				socketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT));
				
				//������ �����ߴ��� Ȯ��
				if (socketChannel.isConnected()) {
					//������ ����
					socketChannel.write(helloBuffer);
					
					while (socketChannel.read(buffer) != -1) {
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
							System.out.println("50�� ���� �Ǿ����ϴ�! socket channel�� �ݰڽ��ϴ�.");
							break;
						}else {
							randomBuffer = ByteBuffer.wrap("���� ���� : ".concat(String.valueOf(r)).getBytes());
							socketChannel.write(randomBuffer);
						}
					}
					
					
				}else {
					System.out.println("***** ������ �� �� �����ϴ�!! *****");
				}
			}else {
				System.out.println("***** socket channel�� ������ �����ϴ�. *****");
			}
			
			//ä���� �ݵ�� �ݾ� �ش�.
			socketChannel.close();
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	

}
