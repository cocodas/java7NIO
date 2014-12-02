package java7.nio2.chapter8.blockingTCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MainServerTest {

	public static void main(String[] args) {
		final int DEFAULT_PORT = 5555;
		final String IP = "127.0.0.1";
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		
			try {
				//새로운 서버 소켓 채널을 생성한다.
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				
				//서버 소켓 채널 생성이 성공했으면 작업을 계속한다.
				if (serverSocketChannel.isOpen()) {
					
					//블로킹 모드를 설정한다.
					serverSocketChannel.configureBlocking(true);
					//몇가지 옵션을 설정한다.
					serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
					serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
					//서버 소켓 채널을 로컬 주소로 바인딩한다.
					serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
					
					//클라이언트를 기다리는동안 대기 메세지를 표시 한다.
					System.out.println("연결 중입니다. 기다려 주십시요....");
					
					//유입 연결을 기다린다.
					while (true) {
						try {
							SocketChannel socketChannel = serverSocketChannel.accept();
							
							System.out.println(socketChannel.getRemoteAddress() + "로 부터 연결 되었습니다. ");
							
							//데이터 전송
							while (socketChannel.read(buffer) != -1) {
								buffer.flip();
								socketChannel.write(buffer);
								
								if (buffer.hasRemaining()) {
									buffer.compact();
								}else {
									buffer.clear();
								}
								
							}
							
						} catch (IOException e) {
						}
					}					
				}else {
					System.out.println("server socket channel을 열수 없습니다!!");
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}

}
