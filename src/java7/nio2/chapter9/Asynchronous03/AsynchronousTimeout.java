package java7.nio2.chapter9.Asynchronous03;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsynchronousTimeout {
	
	//���� �б�� Future Ÿ�� �ƿ�
	ByteBuffer buffer = ByteBuffer.allocate(10000);
	int bytesRead = 0;
	Future<Integer> result = null;
	
	public void asynchronousTimeout(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			
			result = asynchronousFileChannel.read(buffer, 0);
			
			bytesRead = result.get(1, TimeUnit.NANOSECONDS);
			
			if (result.isDone()) {
				System.out.println("����� ���� �� �� �ֽ��ϴ�!!!");
				System.out.println("Read done : " + "[ " + result.get() + " ]");
			}
			
		} catch (Exception e) {
			if (e instanceof TimeoutException) {
				if (result != null) {
					result.cancel(true); //�۾��� ��������� ���
				}
				System.out.println("����� �����Ҽ� �����ϴ�.!");
				System.out.println("�б⸦ ��� �Ͻðڽ��ϱ� ? " +result.isCancelled());
				System.out.println("Read bytes : " + "[ " + bytesRead + " ]");
			}else {
				System.err.println(e);
			}
		}
	}
	

}
