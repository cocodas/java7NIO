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
		
		//새 채널 생성
		try {
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			// 채널 열렸는지 확인
			if (datagramChannel.isOpen()) {

				//멀티캐스트에 사용할 network interface를 가지고 온다
				NetworkInterface networkInterface = NetworkInterface.getByName("net6");
				
				//몇가지 옵션 설정
				datagramChannel.setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface);
				datagramChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				//채널을 주소에 바인딩
				datagramChannel.bind(new InetSocketAddress(DEFAULT_PORT));
				System.out.println("Data Time server가 준비 중... 곧 데이터를 보내겠습니다...");
				
				//데이터 전송
				while (true) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					
					System.out.println("데이터를 보내는 중.....");
					datetime = ByteBuffer.wrap(new Date().toString().getBytes());
					datagramChannel.send(datetime, new InetSocketAddress(InetAddress.getByName(GROUP), DEFAULT_PORT));
					datetime.flip();
				}
			}else {
				System.out.println("채널을 열수 없습니다!!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
