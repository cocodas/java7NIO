package java7.nio2.chapter7.seekableByteChannel04;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SeekableMain {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "Christopher.txt" );
		
		Seekable writer = new Seekable();
		
		writer.seekableByteChanell(path);
	}

}
