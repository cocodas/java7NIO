package java7.nio2.chapter9.Asynchronous02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousWrite {
	
	//파일 쓰기와 Future
	
	ByteBuffer buffer = ByteBuffer.wrap("\nIf a variable is declared with the volatile keyword then it is guaranteed that any thread that reads the field will see the most recently written value. The volatile keyword will not perform any mutual exclusive lock on the variable.As of Java 5 write access to a volatile variable will also update non-volatile variables which were modified by the same thread. This can also be used to update values within a reference variable, e.g. for a volatile variable person. In this case you must use a temporary variable person and use the setter to initialize the variable and then assign the temporary variable to the final variable. This will then make the address changes of this variable and the values visible to other threads.".getBytes());
	
	public void asynchronousWrite(Path path) {
		
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
			
			Future<Integer> result = asynchronousFileChannel.write(buffer, 3771);
			
			while (!result.isDone()) {
				System.out.println("쓰는 동안 다른일을 하는 중입니다.");
			}
			
			System.out.println("Written done : " + "[ " + result.isDone() + " ]");
			System.out.println("Bytes Written : " + "[ " + result.get() + " ]");
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
