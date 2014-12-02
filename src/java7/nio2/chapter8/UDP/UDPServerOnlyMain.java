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
				//새 데이터그램 채널을 생성
				DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
				
				//채널이 성공적으로 열렸는지 확인
				if (datagramChannel.isOpen()) {
					
					System.out.println("Echo Sercer가 성공적으로 열렸습니다.");
					
					//몇가지 옵션을 설정
					datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
					datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
					
					//채널을 로컬 주소에 바인딩
					datagramChannel.bind(new InetSocketAddress(LOCAL_IP, LOCAL_PORT));
					System.out.println("Echo Server는 " + datagramChannel.getLocalAddress() + "에 바인딩 하였습니다.");
					System.out.println("Echo Server은 echo할 준비가 되었습니다.");
					
					//데이터 팻킷 전송
					while (true) {
						SocketAddress clientAddress = datagramChannel.receive(echoText);
						echoText.flip();
						
						System.out.println("[ " + clientAddress.toString() + " ]" + "로부터 " + "[ " + echoText.limit() + " ]bytes 크기의 데이터를 받았습니다!! 이 데이터를 다시 돌려 보내겠습니다!!"  );
						datagramChannel.send(echoText, clientAddress);
						
						echoText.clear();	
					}
				}else {
					System.out.println(" 채널이 열리지 않았습니다!!! ");
				}
			} catch (Exception ex) {
				if (ex instanceof ClosedChannelException) {
					System.err.println("채널이 예상치 못하게 닫혔습니다...");
				}
				if (ex instanceof SecurityException) {
					System.err.println("SecurityException 발생!!!");
				}
				if (ex instanceof IOException) {
					System.err.println("I/O 에러 발생!!!");
				}
				System.err.println("\n" + ex);
		}
	}
}
