package java7.nio2.chapter9.Asynchronous08;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class AsynchronousExecutorService {
	
	private static Set withOptions(){
		final Set options = new TreeSet<>();
		options.add(StandardOpenOption.READ);
		return options;
	}
	
	final int THREADS = 5;
	ExecutorService taskExecutor = Executors.newFixedThreadPool(THREADS);
	
	String encoding = System.getProperty("file.encoding");
	List<Future<ByteBuffer>> list = new ArrayList<>();
	int sheep = 0;
	
	public void executorService(Path path) {
		
		try {
			final AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, withOptions(), taskExecutor);
			
			for (int i = 0; i < 50; i++) {
				Callable<ByteBuffer> worker = new Callable<ByteBuffer>() {
					
					@Override
					public ByteBuffer call() throws Exception {
						ByteBuffer buffer = ByteBuffer.allocateDirect(ThreadLocalRandom.current().nextInt(100, 200));
						asynchronousFileChannel.read(buffer, ThreadLocalRandom.current().nextInt(0, 100));
						
						return buffer;
					}
				};
				Future<ByteBuffer> future = taskExecutor.submit(worker);
				list.add(future);
			}
			
			//�ͽ�ť�Ͱ� ���ο� �����带 �������� �ʰ� �ϰ�
			//ť�� �ִ� ���� �����带 ��� ������.
			
			taskExecutor.shutdown();
			
			//�����尡 ��� ���������� ����Ѵ�.
			while (!taskExecutor.isTerminated()) {
				//���۰� �غ� �ɶ����� �ٸ� �۾��� �Ѵ�
				System.out.println("���۰� ä������ ���� ���� ��������!! �� " +"[ " + (sheep += 1) + " ] ����");
			}
			
			System.out.println("\n\n���۰� �� á��!! ���� ������ �����̴�....\n\n");
			
			for (Future<ByteBuffer> future : list) {
				
				ByteBuffer buffer = future.get();
				
				System.out.println("\n\n" + buffer);
				System.out.println("_______________________________________________________________");
				buffer.flip();
				System.out.println(Charset.forName(encoding).decode(buffer));
				buffer.clear();
				
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	

}
