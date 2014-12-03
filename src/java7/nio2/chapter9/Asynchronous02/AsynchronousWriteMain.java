package java7.nio2.chapter9.Asynchronous02;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousWriteMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		
		AsynchronousWrite write = new AsynchronousWrite();
		write.asynchronousWrite(path);
		
	}

}
