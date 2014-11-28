package java7.nio2.chapter7.seekableByteChannel02;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SeekableByteChannelMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "story.txt" );
		SeekableByteChannel writer = new SeekableByteChannel();
		
		writer.seekableByteChannel(path);
	}

}
