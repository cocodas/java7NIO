package java7.nio2.chapter9.Asynchronous05;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousCompletionHandlerMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		
		AsynchronousCompletionHandler handler = new AsynchronousCompletionHandler();
		
		handler.completionHandler(path);
		
	}

}
