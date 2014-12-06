package java7.nio2.chapter9.Asynchronous06;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousLockMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		
		AsynchronousLock lock = new AsynchronousLock();
		lock.AsynchronousLock(path);
		
	}

}
