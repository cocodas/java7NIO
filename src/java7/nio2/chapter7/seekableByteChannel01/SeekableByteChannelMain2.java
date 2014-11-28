package java7.nio2.chapter7.seekableByteChannel01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SeekableByteChannelMain2 {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "story.txt" );
		SeekableByteChannel reader = new SeekableByteChannel();
		
		try {
			reader.seekableChannel(path);
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
	}

}
