package java7.nio2.chapter9.Asynchronous09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.HostnameVerifier;

public class AsynchronousEchoServer {
	
	final int DEFAUALT_PORT = 5555;
	final String IP = "192.168.85.1";
	
	public void futureServer() {
		
		//기본 그룹에 바인딩된 비동기 서버 소켓 채널을 생성한다.
		try {
			AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			
			//생성 됐는지 확인
			if (asynchronousServerSocketChannel.isOpen()) {
				
				//몇 가지 옵션 설정 설정 하지 않으면 기본으로 적용
				asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
				asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				
				//비동기 서버 소켓 채널을 로컬 주소에 바인딩
				asynchronousServerSocketChannel.bind(new InetSocketAddress(IP, DEFAUALT_PORT));
				
				//클라이언트를 기다린다는 대기 메세지 표시
				System.out.println("연결을 기다리고 있습니다....");
				
				//클라이언트 유입 무한 루프
				while (true) {
					Future<AsynchronousSocketChannel> asynchronousSocketChannelFuture = asynchronousServerSocketChannel.accept();
					
					try {
						final AsynchronousSocketChannel asynchronousSocketChannel = asynchronousSocketChannelFuture.get();

						System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ] 로부터 연결 되었습니다.");
						
						final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
						
						while (asynchronousSocketChannel.read(buffer).get() != -1) {
							
							buffer.flip();
							
							asynchronousSocketChannel.write(buffer).get();
							//System.out.println(buffer);
							
							if (buffer.hasRemaining()) {
								buffer.compact();
							} else {
								buffer.clear();
							}
						}
						
						System.out.println("[ " + asynchronousSocketChannel.getRemoteAddress() + " ] 로 성공적으로 되돌려 주었습니다!" );
						
					} catch (IOException | InterruptedException | ExecutionException e) {
						System.err.println(e);
					} 
				}
			}else {
				System.out.println("비동기 서버 소켓 채널이 열리지 않았습니다!!");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
