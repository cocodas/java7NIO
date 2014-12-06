package java7.nio2.chapter9.Asynchronous09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AsynchronousEchoClient extends ClientBasicSetting{

	public void echoCilent() {
		try {
			AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
			
			if (asynchronousSocketChannel.isOpen()) {
				asynchronousSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
				asynchronousSocketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
				asynchronousSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
				
				Void connect = asynchronousSocketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT)).get();
				
				if (connect == null) {
					System.out.println("Local Address : " + "[ " + asynchronousSocketChannel.getLocalAddress() + " ]" );
					System.out.println("ReMote Address : " + "[ " + asynchronousSocketChannel.getRemoteAddress() + " ]" );
					
					//������ ����
					asynchronousSocketChannel.write(helloBuffer).get();
					
					while (asynchronousSocketChannel.read(buffer).get() != -1) {
						buffer.flip();
						
						charBuffer = decoder.decode(buffer);
					
						System.out.println(charBuffer);
						
						if (buffer.hasRemaining()) {
							buffer.compact();
						}else {
							buffer.clear();
						}
						
						int r = new Random().nextInt(100);
						
						if (r == 50) {
							System.out.println("���� 50�� ���Խ��ϴ�. �񵿱� ���� ä���� �ݰڽ��ϴ�.");
							break;
						}else {
							randomBuffer = ByteBuffer.wrap("���� ���� : ".concat(String.valueOf(r)).getBytes());
							asynchronousSocketChannel.write(randomBuffer).get();
						}
					}
				} else {
					System.out.println("������ �̷������ �ʾҽ��ϴ�.");
				}
			}else {
				System.out.println("�񵿱� ���� ä���� ������ �ʾҽ��ϴ�.");
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			System.err.println(e);
		}
	}
}
