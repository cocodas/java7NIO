package java7.nio2.chapter9.Asynchronous08;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousExecutorServiceMain {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "Asynchronous", "AsynchronousDefine.txt");
		AsynchronousExecutorService asynchronousExecutorService = new AsynchronousExecutorService();
		asynchronousExecutorService.executorService(path);
	}

}
