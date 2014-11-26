package java7.nio2.chapter6.watchService01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WatchRafaelnadalNain {

	public static void main(String[] args) {
		final Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal");
		WatchRafaelnadal watch = new WatchRafaelnadal();
		
		try {
			watch.watchRNDir(path);
		} catch (IOException | InterruptedException ex) {
			System.err.println(ex);
		}
	}

}
