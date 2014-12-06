package java7.nio2.chapter9.Asynchronous10;

public class AsynchronousCompletionHandlerServerMain {

	public static void main(String[] args) {

		AsynchronousCompletionHandlerServer handlerServer = new AsynchronousCompletionHandlerServer();
		handlerServer.completionHandlerServer();
	}

}
