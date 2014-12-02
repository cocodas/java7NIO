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
		
		//open() �޼��带 ȣ���ؼ� Selector�� ServerSocketChannel�� ����.
		try {
			Selector selector = Selector.open();
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			
			//�����Ϳ� ���� ���� ä���� ���������� ���ȴ��� Ȯ���Ѵ�.
			if ((serverSocketChannel.isOpen()) && (selector.isOpen())) {
				
				//����ŷ ��� ����
				serverSocketChannel.configureBlocking(false);
				
				//��� �ɼ� ����
				serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 256*1024);
				serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				
				//���� ���� ä���� ��Ʈ�� ���ε� �Ѵ�.
				serverSocketChannel.bind(new InetSocketAddress(DEFAULT_PORT));
				
				//���� ä���� �־��� �����Ϳ� ����Ѵ�.
				serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
				
				//��� �޼����� ǥ���Ѵ�.
				System.out.println("�������Դϴ�... ��ø� ��ٷ� �ֽʽÿ�!....");
				
				while (true) {
					//���� �̺�Ʈ�� ��ٸ���.
					selector.select();
					
					//���õ� Ű ���տ� ���� ���� ó���� �� �ִ�.
					Iterator keys = selector.selectedKeys().iterator();
					
					while (keys.hasNext()) {
						SelectionKey key = (SelectionKey) keys.next();
						
						//���� Ű�� �ݺ��ؼ� ���°��� ���� ���� ó���� Ű�� �����Ѵ�.
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
				System.out.println("sever socket channel �Ǵ� selrctor�� ������ �ʾҽ��ϴ�.");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	//isAcceptable�� true�� ��ȯ���� ���� ó��
	private void acceptOP(SelectionKey key, Selector selector) throws IOException {
		
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverChannel.accept();
		socketChannel.configureBlocking(false);
		
		System.out.println(socketChannel.getRemoteAddress() + "�� ���� �Ͽ����ϴ�..");
		
		//ȯ�� �޼���
		socketChannel.write(ByteBuffer.wrap("� ������!!\n".getBytes("UTF-8")));
		
		// I/O ó���� ���� ä���� �����Ϳ� ����Ѵ�.
		keepDataTrack.put(socketChannel, new ArrayList<byte[]>());
		socketChannel.register(selector, SelectionKey.OP_READ);		
	}
	
	//isReadable�� true�� ��ȯ���� ���� ó��
	private void readOP(SelectionKey key){
		try {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			buffer.clear();
			
			int numRead = -1;
			try {
				numRead = socketChannel.read(buffer);				
			} catch (IOException e) {
				System.err.println("������ ���� ����!!");
			}
			
			if (numRead == -1) {
				this.keepDataTrack.remove(socketChannel);
				System.out.println(socketChannel.getRemoteAddress() + "�� ���� ������ �������ϴ�!!");
				socketChannel.close();
				key.cancel();
				return;
			} 
			
			byte[] data = new byte[numRead];
			System.arraycopy(buffer.array(), 0, data, 0, numRead);
			System.out.println(new String(data, "UTF-8") + "from" + socketChannel.getRemoteAddress());
			
			//Ŭ���̾�Ʈ�� �����Ѵ�
			doEchoJob(key, data);

		} catch (IOException e) {
			System.err.println(e);
		}		
	}
	
	//isWritable�� true�� ��ȯ���� ���� ó��.
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
