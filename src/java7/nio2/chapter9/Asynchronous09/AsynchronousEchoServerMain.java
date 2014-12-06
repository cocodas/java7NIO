package java7.nio2.chapter9.Asynchronous09;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AsynchronousEchoServerMain {

	public static void main(String[] args) {
		
		AsynchronousEchoServer echoServer = new AsynchronousEchoServer();
		echoServer.futureServer();
		
	}

}
