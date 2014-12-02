package java7.nio2.chapter8.nonBlockingTCP;

public class NonBlockingTCPServerMain {

	public static void main(String[] args) {

		NonBlockingTCPServer server = new NonBlockingTCPServer();
		server.startEchoServer();
	}

}
