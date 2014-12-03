package java7.nio2.chapter9.Asynchronous01;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousReadMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		
		AsynchronousRead read = new AsynchronousRead();
		read.asynchronousRead(path);
		
	}

}
