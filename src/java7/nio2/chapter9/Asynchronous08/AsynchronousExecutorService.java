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
			
			//익스큐터가 새로운 스레드를 수락하지 않게 하고
			//큐에 있는 기존 스레드를 모두 끝낸다.
			
			taskExecutor.shutdown();
			
			//스레드가 모두 끝날때까지 대기한다.
			while (!taskExecutor.isTerminated()) {
				//버퍼가 준비 될때까지 다른 작업을 한다
				System.out.println("버퍼가 채워지는 동안 양을 세워보자!! 양 " +"[ " + (sheep += 1) + " ] 마리");
			}
			
			System.out.println("\n\n버퍼가 다 찼다!! 여기 버퍼의 내용이다....\n\n");
			
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
