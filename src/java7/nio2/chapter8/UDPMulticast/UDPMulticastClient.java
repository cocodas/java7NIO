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
		
		//새 채널 생성
		try {
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			InetAddress group = InetAddress.getByName(GROUP);
			
			//그룹 주소가 멀티캐스트인가 확인
			if (group.isMulticastAddress()) {
		
				if (datagramChannel.isOpen()) {
					
					//멀티 캐스트에 사용할 네트워크 인터페이스를 가져온다.
					NetworkInterface networkInterface = NetworkInterface.getByName("net6");
					
					//옵션 설정
					datagramChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
					//채널 주소 바인딩
					datagramChannel.bind(new InetSocketAddress(DEFAULT_PORT));
					//멀티 캐스트 그룹에 가입하고 데이터그램 수신을 준비한다.
					MembershipKey key = datagramChannel.join(group, networkInterface);
					
					//데이터 그램을 기다린다
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
				System.out.println("채널을 열수가 없습니다!!");
				}
		}else {
			System.out.println("multicast 주소 아닙니다!!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

