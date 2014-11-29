package java7.nio2.chapter7.fileChannel01;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelMapMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "javaTest", "Button.txt");
		
		FileChannelMap map = new FileChannelMap();
		
		map.mapped(path);
		//System.out.println(map.buffer);
		
	}

}
