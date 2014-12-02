package java7.nio2.chapter8.nonBlockingTCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NonBlockingTCPServer {
	
	private Map<SocketChannel, List<byte[]>> keepDataTrack = new HashMap<>();
	private ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);
	
	public void startEchoServer() {
		final int DEFAULT_PORT = 5555;
		
		//open() 메서드를 호출해서 Selector과 ServerSocketChannel을 연다.
		try {
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			
			//셀렉터와 서버 소켓 채널이 성공적으로 열렸는지 확인한다.
			if ((serverSocketChannel.isOpen()) && (selector.isOpen())) {
				
				//논블로킹 모드 설정
				serverSocketChannel.configureBlocking(false);
				
				//몇가지 옵션 설정
				serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 256*1024);
				serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				
				//서버 소켓 채널을 포트와 바인딩 한다.
				serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT));
				
				//현제 채널을 주어진 셀렉터에 등록한다.
				serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
				
				//대기 메세지를 표시한다.
				System.out.println("연결중입니다... 잠시만 기다려 주십시요!....");
				
				while (true) {
					//유입 이벤트를 기다린다.
					selector.select();
					
					//선택된 키 집합에 대해 뭔가 처리할 게 있다.
					Iterator keys = selector.selectedKeys().iterator();
					
					while (keys.hasNext()) {
						SelectionKey key = (SelectionKey) keys.next();
						
						//같은 키가 반복해서 오는것을 막기 위해 처리한 키는 제거한다.
						keys.remove();
						
						if (!key.isValid()) {
							continue;
						}
						
						if (key.isAcceptable()) {
							acceptOP(key, selector);
						}else if (key.isReadable()) {
							this.readOP(key);
						}else if (key.isWritable()) {
							this.writeOP(key);
						}
					}
				}
			}else {
				System.out.println("sever socket channel 또는 selrctor가 열리지 않았습니다.");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	//isAcceptable이 true를 반환했을 때의 처리
	private void acceptOP(SelectionKey key, Selector selector) throws IOException {
		
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverChannel.accept();
		socketChannel.configureBlocking(false);
		
		System.out.println(socketChannel.getRemoteAddress() + "로 연결 하였습니다..");
		
		//환영 메세지
		socketChannel.write(ByteBuffer.wrap("어서 오세요!!\n".getBytes("UTF-8")));
		
		// I/O 처리를 위해 채널을 셀렉터에 등록한다.
		keepDataTrack.put(socketChannel, new ArrayList<byte[]>());
		socketChannel.register(selector, SelectionKey.OP_READ);		
	}
	
	//isReadable이 true를 반환했을 때의 처리
	private void readOP(SelectionKey key){
		try {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			buffer.clear();
			
			int numRead = -1;
			try {
				numRead = socketChannel.read(buffer);				
			} catch (IOException e) {
				System.err.println("읽을수 없는 에러!!");
			}
			
			if (numRead == -1) {
				this.keepDataTrack.remove(socketChannel);
				System.out.println(socketChannel.getRemoteAddress() + "에 의해 연결이 닫혔습니다!!");
				socketChannel.close();
				key.cancel();
				return;
			} 
			
			byte[] data = new byte[numRead];
			System.arraycopy(buffer.array(), 0, data, 0, numRead);
			System.out.println(new String(data, "UTF-8") + "from" + socketChannel.getRemoteAddress());
			
			//클라이언트에 응답한다
			doEchoJob(key, data);

		} catch (IOException e) {
			System.err.println(e);
		}		
	}
	
	//isWritable이 true를 반환했을 때의 처리.
	private void writeOP(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		
		List<byte[]> channelData = keepDataTrack.get(socketChannel);
		Iterator<byte[]> its = channelData.iterator();
		
		while (its.hasNext()) {
			byte[] it = its.next();
			its.remove();
			socketChannel.write(ByteBuffer.wrap(it));
		}
		
		key.interestOps(SelectionKey.OP_READ);

	}
	
	private void doEchoJob(SelectionKey key, byte[] data) {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		List<byte[]> channelData = keepDataTrack.get(socketChannel);
		channelData.add(data);
		
		key.interestOps(SelectionKey.OP_WRITE);
	}
}
