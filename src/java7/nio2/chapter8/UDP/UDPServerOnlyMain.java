package java7.nio2.chapter8.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;

public class UDPServerOnlyMain {

	public static void main(String[] args) {
		final int LOCAL_PORT = 5555;
		final String LOCAL_IP = "192.168.85.1";
		final int MAX_PACKET_SIZE = 65507;
		
		ByteBuffer echoText = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
				
			try {
				//�� �����ͱ׷� ä���� ����
				DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
				
				//ä���� ���������� ���ȴ��� Ȯ��
				if (datagramChannel.isOpen()) {
					
					System.out.println("Echo Sercer�� ���������� ���Ƚ��ϴ�.");
					
					//��� �ɼ��� ����
					datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
					datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
					
					//ä���� ���� �ּҿ� ���ε�
					datagramChannel.bind(new InetSocketAddress(LOCAL_IP, LOCAL_PORT));
					System.out.println("Echo Server�� " + datagramChannel.getLocalAddress() + "�� ���ε� �Ͽ����ϴ�.");
					System.out.println("Echo Server�� echo�� �غ� �Ǿ����ϴ�.");
					
					//������ ��Ŷ ����
					while (true) {
						SocketAddress clientAddress = datagramChannel.receive(echoText);
						echoText.flip();
						
						System.out.println("[ " + clientAddress.toString() + " ]" + "�κ��� " + "[ " + echoText.limit() + " ]bytes ũ���� �����͸� �޾ҽ��ϴ�!! �� �����͸� �ٽ� ���� �����ڽ��ϴ�!!"  );
						datagramChannel.send(echoText, clientAddress);
						
						echoText.clear();	
					}
				}else {
					System.out.println(" ä���� ������ �ʾҽ��ϴ�!!! ");
				}
			} catch (Exception ex) {
				if (ex instanceof ClosedChannelException) {
					System.err.println("ä���� ����ġ ���ϰ� �������ϴ�...");
				}
				if (ex instanceof SecurityException) {
					System.err.println("SecurityException �߻�!!!");
				}
				if (ex instanceof IOException) {
					System.err.println("I/O ���� �߻�!!!");
				}
				System.err.println("\n" + ex);
		}
	}
}
