package java7.nio2.chapter9.Asynchronous07;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousCompletionHandlerLockMain {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		
		AsynchronousCompletionHandlerLock lock = new AsynchronousCompletionHandlerLock();
		lock.completinHandlerLock(path);
	}

}
