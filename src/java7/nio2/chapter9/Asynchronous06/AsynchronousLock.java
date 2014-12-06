package java7.nio2.chapter9.Asynchronous06;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousLock {
	
	ByteBuffer buffer = ByteBuffer.wrap("\nThe Java memory model describes the communication between the memory of the threads and the main memory of the application. It defines the rules how changes in the memory done by threads are propagated to other threads. The Java memory model also defines the situations in which a thread re-fresh its own memory from the main memory.".getBytes());
	
	public void AsynchronousLock(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
			
			Future<FileLock> futureLock = asynchronousFileChannel.lock();
			
			System.out.println("������ ��ױ� ���ؼ� ��ø� ��ٷ��ּ���...");
			
			FileLock lock = futureLock.get();
			//��� ���·ε� ��� ����
			//FileLock lock = asynchronousFileChannel.lock().get();
			
			if (lock.isValid()) {
				Future<Integer> futureWrite = asynchronousFileChannel.write(buffer, 4500);
				System.out.println("���Ⱑ �Ϸ� �ɶ����� ��ٷ��ּ���....");
				int written = futureWrite.get();
				//����ؼ� ��� ����
				//int written = asynchronousFileChannel.write(buffer, 4500).get();
				
				System.out.println("���� " + "[ " + written + " ]" + "bytes�� " + "[ " + path.getFileName() + " ]" + " ���Ͽ� �ۼ� �Ͽ����ϴ�" );
				
				lock.release();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	} 
	
	
	

}
