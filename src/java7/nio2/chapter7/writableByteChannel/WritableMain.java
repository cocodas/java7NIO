package java7.nio2.chapter7.writableByteChannel;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WritableMain {
	public static void main(String[] args) {
		
		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "story.txt" );
		Writable writer = new Writable();
		
		writer.writableByteChannel(path);
		
	}

}
