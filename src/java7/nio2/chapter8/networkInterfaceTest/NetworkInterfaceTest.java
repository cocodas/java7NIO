package java7.nio2.chapter8.networkInterfaceTest;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceTest {
	
	public void networkTest() throws Exception {
		Enumeration enumInterfaces = NetworkInterface.getNetworkInterfaces();
		
		while (enumInterfaces.hasMoreElements()) {
			
			NetworkInterface net = (NetworkInterface) enumInterfaces.nextElement();
			
			System.out.println("Network Interface Display Name : " + "[ " + net.getDisplayName() + " ]");
			System.out.println(net.getDisplayName() + " is up and running ? " + "[ " + net.isUp() + " ]");
			System.out.println(net.getDisplayName() + " Supports Multicast : " + "[ " + net.supportsMulticast() + " ]");
			System.out.println(net.getDisplayName() + " Name : " + "[ " + net.getName() + " ]");
			System.out.println(net.getDisplayName() + " Is Virtual : " + net.isVirtual());
			System.out.println("IP address : ");

			Enumeration enumIP = net.getInetAddresses();
			while (enumIP.hasMoreElements()) {
				InetAddress ip = (InetAddress) enumIP.nextElement();
				System.out.println("IP address : " + "[" + ip + " ]");
			}
			
		}
	}

}
