package java7.nio2.chapter8.blockingTCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Random;

public class BlockingTCPClient extends BasicSetting{
	
	//새 소켓채널 생성한다.
	public void clientSocket() {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			
			//소켓 채널 생성이 성공했으면 계속 진행
			if (socketChannel.isOpen()) {
				//블록킹 모드 설정
				socketChannel.configureBlocking(true);
				//옵션 설정
				socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				socketChannel.setOption(StandardSocketOptions.SO_LINGER, 5);
				
				//이 채널의 소켓에 연결
				socketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT));
				
				//연결이 성공했는지 확인
				if (socketChannel.isConnected()) {
					//데이터 전송
					socketChannel.write(helloBuffer);
					
					while (socketChannel.read(buffer) != -1) {
						buffer.flip();
						
						charBuffer = decoder.decode(buffer);
						System.out.println(charBuffer.toString());
						
						if (buffer.hasRemaining()) {
							buffer.compact();
						}else {
							buffer.clear();
						}
						
						int r = new Random().nextInt(100);
						if (r == 50) {
							System.out.println("50이 생성 되었습니다! socket channel을 닫겠습니다.");
							break;
						}else {
							randomBuffer = ByteBuffer.wrap("임의 숫자 : ".concat(String.valueOf(r)).getBytes());
							socketChannel.write(randomBuffer);
						}
					}
					
					
				}else {
					System.out.println("***** 연결을 할 수 없습니다!! *****");
				}
			}else {
				System.out.println("***** socket channel을 열수가 없습니다. *****");
			}
			
			//채널을 반드시 닫아 준다.
			socketChannel.close();
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	

}
