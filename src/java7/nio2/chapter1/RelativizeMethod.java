package java7.nio2.chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativizeMethod {

	public static void main(String[] args) {
		Path path01 = Paths.get("Button.txt");
		Path path02 = Paths.get("Space.txt");
		
		Path path01_to_path02 = path01.relativize(path02);
		System.out.println("path01 -> path02 : " + path01_to_path02);
		
		Path path02_to_path01 = path02.relativize(path01);
		System.out.println("path02 -> path01 : " + path02_to_path01);
		
		
	}

}
