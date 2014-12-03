package java7.nio2.chapter9.Asynchronous03;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousTimeoutMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		
		AsynchronousTimeout read = new AsynchronousTimeout();
		read.asynchronousTimeout(path);
		
	}

}
