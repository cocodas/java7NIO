package java7.nio2.chapter9.Asynchronous09;

public class AsynchronousExecutorEchoServerMain {

	public static void main(String[] args) {

		AsynchronousExecutorEchoServer executorEchoServer = new AsynchronousExecutorEchoServer();
		executorEchoServer.futureServer();
	}

}
