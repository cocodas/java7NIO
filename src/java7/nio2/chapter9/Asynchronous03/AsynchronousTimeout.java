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
	
	//파일 읽기와 Future 타임 아웃
	ByteBuffer buffer = ByteBuffer.allocate(10000);
	int bytesRead = 0;
	Future<Integer> result = null;
	
	public void asynchronousTimeout(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			
			result = asynchronousFileChannel.read(buffer, 0);
			
			bytesRead = result.get(1, TimeUnit.NANOSECONDS);
			
			if (result.isDone()) {
				System.out.println("결과를 도출 할 수 있습니다!!!");
				System.out.println("Read done : " + "[ " + result.get() + " ]");
			}
			
		} catch (Exception e) {
			if (e instanceof TimeoutException) {
				if (result != null) {
					result.cancel(true); //작업을 명시적으로 취소
				}
				System.out.println("결과를 도출할수 없습니다.!");
				System.out.println("읽기를 취소 하시겠습니까 ? " +result.isCancelled());
				System.out.println("Read bytes : " + "[ " + bytesRead + " ]");
			}else {
				System.err.println(e);
			}
		}
	}
	

}
