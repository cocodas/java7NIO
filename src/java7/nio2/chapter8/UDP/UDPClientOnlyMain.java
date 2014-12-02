package java7.nio2.chapter8.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class UDPClientOnlyMain {

	public static void main(String[] args) {
			
		final int REMOTE_PORT = 5555;
		final String REMOTE_IP = "192.168.85.1";
		final int MAX_PACKET_SIZE = 65507;

		CharBuffer charBuffer = null;
		Charset charset = Charset.defaultCharset();
		CharsetDecoder decoder = charset.newDecoder();
		ByteBuffer textToEcho = ByteBuffer.wrap("Echo this : ���� �׶��ϰ� ������ ���� �Դϴ�.".getBytes());
		ByteBuffer echoedText = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);

	try {
		//�� �����ͱ׷� ä���� �����Ѵ�
		DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);

			//���������� ���ȴ��� Ȯ��
			if (datagramChannel.isOpen()) {
				//��� ����
				datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
				datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
				
				//������ ��Ŷ ����
				int sent = datagramChannel.send(textToEcho, new InetSocketAddress(REMOTE_IP, REMOTE_PORT));
			
				System.out.println("���������� " + "[ " + sent + " ]" + "bytes�� Echo Server�� ���½��ϴ�.");
				
				datagramChannel.receive(echoedText);
				Thread.sleep(5000);
				echoedText.flip();
				charBuffer = decoder.decode(echoedText);
				System.out.println(charBuffer.toString());
				echoedText.clear();
				
			}else {
				System.out.println("ä���� ���� �����ϴ�!!");
				}
			} catch (Exception e) {
				if (e instanceof ClosedChannelException) {
					System.err.println("ä���� ����ġ ���� ��Ȳ���� �������ϴ�.");
			}
				if (e instanceof SecurityException) {
					System.err.println("SecurityException �߻�!!!");
			}
				if (e instanceof IOException) {
					System.err.println("I/O ���� �߻�!!!");
			}
			
				System.err.println("\n" + e);
			}
	}

}
