package java7.nio2.chapter6.watchService02;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WatchRecursiveRafaelNadalMain {

	public static void main(String[] args) {
		final Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal");
		WatchRecursiveRafaelNadal watch = new WatchRecursiveRafaelNadal();
		
		try {
			watch.watchRNDir(path);
		} catch (IOException | InterruptedException ex) {
			System.err.println(ex);
		}
	}

}
