package java7.nio2.chapter6.watchService03;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SecurityWatchMain {

	public static void main(String[] args) {
		final Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal");
		SecurityWatch watch = new SecurityWatch();
		
		try {
			watch.watchVideoCamera(path);
		} catch (IOException | InterruptedException ex) {
			System.err.println(ex);
		}
		
	}
}
