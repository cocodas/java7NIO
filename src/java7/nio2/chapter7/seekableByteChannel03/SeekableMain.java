package java7.nio2.chapter7.seekableByteChannel03;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SeekableMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "story.txt" );
		Seekable reader = new Seekable();
		
		reader.seekableByteChannel(path);
		
	}

}
