package java7.nio2.chapter8.nonBlockingTCP;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class NonBlockingTCPClientMain {

	public static void main(String[] args) {
		final int DEFAULT_PORT = 5555;
		final String IP = "127.0.0.1";
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(2*1024);
		ByteBuffer randomBuffer;
		CharBuffer charBuffer;
		
		Charset charset = Charset.defaultCharset();
		CharsetDecoder decoder = charset.newDecoder();
		
		//open()메서드를 호출해서 Selector과 ServerSocketChannel을 연다.
		try {
			Selector selector = Selector.open();
			SocketChannel socketChannel = SocketChannel.open();
			
			//셀렉터아 서버소켓채널이 성공적으로 열렸는지 확인 한다.
			if ((socketChannel.isOpen()) && (selector.isOpen())) {
				
				//논블럭킹 모드를 설정한다
				socketChannel.configureBlocking(false);
				
				//몇가지 옵션을 설정한다.
				socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
				socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
				
				//현제의 채널을 주어진 셀렉터에 등록 한다.
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
				
				//원격지 호스트에 연결한다.
				socketChannel.connect(new java.net.InetSocketAddress(IP, DEFAULT_PORT));
				
				System.out.println("Localhost : " + socketChannel.getLocalAddress());
				
				//연결을 기다린다.
				while (selector.select(1000) > 0) {
					
					//키를 가져온다.
					Set keys = selector.selectedKeys();
					Iterator its = keys.iterator();
					
					//각키를 처리 한다.
					while (its.hasNext()) {
						
						SelectionKey key = (SelectionKey) its.next();
						
						//현제 키를 제거한다.
						its.remove();
						
						//이 키에 대한 소켓 채널을 가져온다.
						try {
							SocketChannel keySocketChannel = (SocketChannel) key.channel();
							
							//연결 시도
							if (key.isConnectable()) {
								//연결 성공 메세지 발송
								System.out.println("연결 되었습니다!!!");
								
								//미처리 연결을 닫는다.
								if (keySocketChannel.isConnectionPending()) {
									keySocketChannel.finishConnect();
								}
								
								//서버에 읽기, 서버에 쓰기를 처리한다.
								while (keySocketChannel.read(buffer) != -1) {
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
										System.out.println("50이 나왔습니다. socket channel을 닫겠습니다!!");
										socketChannel.close();
										break;
									} else {
										randomBuffer = ByteBuffer.wrap("임의의 수 : ".concat(String.valueOf(r)).getBytes("UTF-8"));
										keySocketChannel.write(randomBuffer);
										try {
											Thread.sleep(1500);
											
										} catch (InterruptedException e) {
										}
									}		
								}
							}
						} catch (IOException e) {
							System.err.println(e);
						}
					}
				}
				
			}else {
				System.out.println("socket channel이나 selector이 열려있지 않습니다.!");
			}	
		} catch (IOException e) {
			System.err.println(e);
		}
	}	
}
