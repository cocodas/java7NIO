package java7.nio2.chapter9.Asynchronous01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousRead {
	
	//���� �б�� Future
	
	ByteBuffer buffer = ByteBuffer.allocate(10000);
	String encoding = System.getProperty("file.encoding");
	
	public void asynchronousRead(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			
			Future<Integer> result = asynchronousFileChannel.read(buffer, 0);
			
			while (!result.isDone()) {
				System.out.println("�д� ���� �ٸ����� �ϴ� ���Դϴ�...");
			}
			
			System.out.println("Read done : " + "[ " + result.isDone() + " ]");
			System.out.println("Bytes read : " + "[ " + result.get() + " ]");
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		buffer.flip();
		System.out.println(Charset.forName(encoding).decode(buffer));
		buffer.clear();
	}

}
