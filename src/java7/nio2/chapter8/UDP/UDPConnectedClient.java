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
	ByteBuffer textToEcho = ByteBuffer.wrap("Echo this : 나는 뚱뚱하고 못생긴 서버 입니다.".getBytes());
	ByteBuffer echoedText = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
	
	public void udpClient() {
		//새 데이터그램 생성
		try {
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			
			//몇가지 옵션 설정
			datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
			datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
			
			//채널이 열렸는지 확인
			if (datagramChannel.isOpen()) {
				
				//원격지 주소와 연결
				datagramChannel.connect(new InetSocketAddress(REMOTE_IP, REMOTE_FORT));
				
				//채널이 성공적으로 연결됐는지 확인
				if (datagramChannel.isConnected()) {
					
					//데이터 패킷 전송
					int sent = datagramChannel.write(textToEcho);
					System.out.println("성공적으로 Echo Server에 " + "[ " + sent + " ]" + "bytes의 데이터를 전송했습니다!!");
					
					datagramChannel.read(echoedText);
					
					echoedText.flip();
					//Thread.sleep(5000);
					charBuffer = decoder.decode(echoedText);
					System.out.println(charBuffer.toString());
					echoedText.clear();
				}else {
					System.out.println("채널이 연결되지 않았습니다.!");
				}
			}else {
				System.out.println("채널을 열수 없습니다!!");
			}
		} catch (Exception e) { 
			if (e instanceof ClosedChannelException) {
				System.err.println("채널이 예상치 못한 일로 닫혔습니다!");
			}
			if (e instanceof SecurityException) {
				System.err.println("security exception 발생!!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O 에러 발생!!");
			}
			
			System.err.println("\n" + e);
		}
	}

}
