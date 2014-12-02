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

import com.ibm.icu.impl.duration.impl.DataRecord.ECountVariant;

public class UDPConnectedClient {
	final int REMOTE_FORT = 5555;
	final String REMOTE_IP = "192.168.85.1";
	final int MAX_PACKET_SIZE = 65507;
	
	CharBuffer charBuffer = null;
	Charset charset = Charset.defaultCharset();
	CharsetDecoder decoder = charset.newDecoder();
	ByteBuffer textToEcho = ByteBuffer.wrap("Echo this : ���� �׶��ϰ� ������ ���� �Դϴ�.".getBytes());
	ByteBuffer echoedText = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
	
	public void udpClient() {
		//�� �����ͱ׷� ����
		try {
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			//��� �ɼ� ����
			datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
			datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
			
			//ä���� ���ȴ��� Ȯ��
			if (datagramChannel.isOpen()) {
				
				//������ �ּҿ� ����
				datagramChannel.connect(new InetSocketAddress(REMOTE_IP, REMOTE_FORT));
				
				//ä���� ���������� ����ƴ��� Ȯ��
				if (datagramChannel.isConnected()) {
					
					//������ ��Ŷ ����
					int sent = datagramChannel.write(textToEcho);
					System.out.println("���������� Echo Server�� " + "[ " + sent + " ]" + "bytes�� �����͸� �����߽��ϴ�!!");
					
					datagramChannel.read(echoedText);
					
					echoedText.flip();
					//Thread.sleep(5000);
					charBuffer = decoder.decode(echoedText);
					System.out.println(charBuffer.toString());
					echoedText.clear();
				}else {
					System.out.println("ä���� ������� �ʾҽ��ϴ�.!");
				}
			}else {
				System.out.println("ä���� ���� �����ϴ�!!");
			}
		} catch (Exception e) { 
			if (e instanceof ClosedChannelException) {
				System.err.println("ä���� ����ġ ���� �Ϸ� �������ϴ�!");
			}
			if (e instanceof SecurityException) {
				System.err.println("security exception �߻�!!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O ���� �߻�!!");
			}
			
			System.err.println("\n" + e);
		}
	}

}
