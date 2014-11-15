package java7_NIO2_chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GetProperty {
	public static void main(String[] args) {
		
		Path path = Paths.get(System.getProperty("user.home"), "download", "Button.txt");
		System.out.println(path);
	}
	
	
	
}
