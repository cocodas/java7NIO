package java7.nio2.chapter8.blockingTCP;

public class BlockingTCPConnectTestMain {

	public static void main(String[] args) {

		BlockingTCPServer tcpServer = new BlockingTCPServer();
		BlockingTCPClient tcpClient = new BlockingTCPClient();
		
		
		System.out.println();
		
		tcpServer.serverSocket();
		tcpClient.clientSocket();
	}

}
