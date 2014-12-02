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
		ByteBuffer textToEcho = ByteBuffer.wrap("Echo this : 나는 뚱뚱하고 못생긴 서버 입니다.".getBytes());
		ByteBuffer echoedText = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);

	try {
		//새 데이터그램 채널을 생성한다
		DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);

			//성공적으로 열렸는지 확인
			if (datagramChannel.isOpen()) {
				//몇가지 설정
				datagramChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
				datagramChannel.setOption(StandardSocketOptions.SO_SNDBUF, 4 * 1024);
				
				//데이터 팻킷 전송
				int sent = datagramChannel.send(textToEcho, new InetSocketAddress(REMOTE_IP, REMOTE_PORT));
			
				System.out.println("성공적으로 " + "[ " + sent + " ]" + "bytes를 Echo Server에 보냈습니다.");
				
				datagramChannel.receive(echoedText);
				Thread.sleep(5000);
				echoedText.flip();
				charBuffer = decoder.decode(echoedText);
				System.out.println(charBuffer.toString());
				echoedText.clear();
				
			}else {
				System.out.println("채널을 열수 없습니다!!");
				}
			} catch (Exception e) {
				if (e instanceof ClosedChannelException) {
					System.err.println("채널이 예상치 못한 상황으로 닫혔습니다.");
			}
				if (e instanceof SecurityException) {
					System.err.println("SecurityException 발생!!!");
			}
				if (e instanceof IOException) {
					System.err.println("I/O 에러 발생!!!");
			}
			
				System.err.println("\n" + e);
			}
	}

}
