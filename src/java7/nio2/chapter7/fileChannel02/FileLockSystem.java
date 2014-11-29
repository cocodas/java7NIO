package java7.nio2.chapter7.fileChannel02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class FileLockSystem {
	
	ByteBuffer buffer = ByteBuffer.wrap("Welcome to cocodas !!".getBytes());
	
	public void fileLock(Path path) {
		try {
			FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE));
			
			//���Ͽ� ����� ����� ���� ���� ä���� ����Ѵ�.
			//�� �޼���� ����� ������ ������ �����Ѵ�.
			FileLock lock = fileChannel.lock();
			
			//����(blocking) ���� ����� ȹ���Ϸ� �Ѵ�.
			//�� �޼���� ������ �̹� ��� �ִٸ� null�̳� ���ܸ� �߻��Ѵ�.
			
//			try {
//				lock = fileChannel.tryLock();
//				
//			} catch (OverlappingFileLockException e) {
//				System.err.println(e);
//			�� �����峪 ����ӽſ��� ������ �̹� ��� �ִ�.
//			}
			
			if (lock.isValid()) {
				System.out.println("@@@ ���Ⱑ ����ִ� ���� �Դϴ�!! @@@");
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					System.err.println(e);
				}
				
				fileChannel.position(0);
				fileChannel.write(buffer);
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}
			
			//����� ���� �Ѵ�
			lock.release();
			
			System.out.println("\n����� ���� �Ǿ����ϴ�!!!");

		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
	
	

}
