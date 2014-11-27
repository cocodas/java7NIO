package java7.nio2.chapter6.watchService04;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WatchPrinterTrayMain {

	public static void main(String[] args) {
		final Path path = Paths.get(System.getProperty("user.home"), "printerTray");
		WatchPrinterTray watch = new WatchPrinterTray();
		
		try {
			watch.watchTray(path);
		} catch (IOException | InterruptedException ex) {
			System.err.println(ex);
		}
		
	}

}
