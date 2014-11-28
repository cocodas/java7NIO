package java7.nio2.chapter7.readableByteChannel;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadableMain {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "button.txt" );
		
		Readable reader = new Readable();
		
		reader.readableByteChannel(path);
		
		
	}

}
