package java7.nio2.chapter8.UDPMulticast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class UDPMulticastClient {
	
	final int DEFAULT_PORT = 5555;
	final String GROUP = "225.4.5.6";
	final int MAX_PACKET_SIZE = 65507;
	
	CharBuffer charBuffer = null;
	Charset charset = Charset.defaultCharset();
	CharsetDecoder decoder = charset.newDecoder();
	ByteBuffer datetime = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
	
	public void multyCastClient() {
		
		//�� ä�� ����
		try {
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			InetAddress group = InetAddress.getByName(GROUP);
			
			//�׷� �ּҰ� ��Ƽĳ��Ʈ�ΰ� Ȯ��
			if (group.isMulticastAddress()) {
		
				if (datagramChannel.isOpen()) {
					
					//��Ƽ ĳ��Ʈ�� ����� ��Ʈ��ũ �������̽��� �����´�.
					NetworkInterface networkInterface = NetworkInterface.getByName("net6");
					
					//�ɼ� ����
					datagramChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
					//ä�� �ּ� ���ε�
					datagramChannel.bind(new InetSocketAddress(DEFAULT_PORT));
					//��Ƽ ĳ��Ʈ �׷쿡 �����ϰ� �����ͱ׷� ������ �غ��Ѵ�.
					MembershipKey key = datagramChannel.join(group, networkInterface);
					
					//������ �׷��� ��ٸ���
					while (true) {
						if (key.isValid()) {
							
							datagramChannel.receive(datetime);
							datetime.flip();
							charBuffer = decoder.decode(datetime);
							System.out.println(charBuffer.toString());
							datetime.clear();
						}else {
							break;
						}
					}
			}else {
				System.out.println("ä���� ������ �����ϴ�!!");
				}
		}else {
			System.out.println("multicast �ּ� �ƴմϴ�!!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

