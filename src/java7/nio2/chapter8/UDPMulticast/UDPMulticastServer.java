package java7.nio2.chapter8.UDPMulticast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;

public class UDPMulticastServer {
	
	final int DEFAULT_PORT = 5555;
	final String GROUP = "225.4.5.6";
	ByteBuffer datetime;
	
	public void multyCastServer() {
		
		//�� ä�� ����
		try {
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			// ä�� ���ȴ��� Ȯ��
			if (datagramChannel.isOpen()) {

				//��Ƽĳ��Ʈ�� ����� network interface�� ������ �´�
				NetworkInterface networkInterface = NetworkInterface.getByName("net6");
				
				//��� �ɼ� ����
				datagramChannel.setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface);
				datagramChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				//ä���� �ּҿ� ���ε�
				datagramChannel.bind(new InetSocketAddress(DEFAULT_PORT));
				System.out.println("Data Time server�� �غ� ��... �� �����͸� �����ڽ��ϴ�...");
				
				//������ ����
				while (true) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					
					System.out.println("�����͸� ������ ��.....");
					datetime = ByteBuffer.wrap(new Date().toString().getBytes());
					datagramChannel.send(datetime, new InetSocketAddress(InetAddress.getByName(GROUP), DEFAULT_PORT));
					datetime.flip();
				}
			}else {
				System.out.println("ä���� ���� �����ϴ�!!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
