package java7.nio2.chapter7.seekableByteChannel06;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SeekableMain {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "Inception.txt" );
		
		Seekable copyWriter = new Seekable();
		copyWriter.seekableByteChannel(path);
		
	}

}
