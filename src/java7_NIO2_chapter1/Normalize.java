package java7_NIO2_chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Normalize {

	public static void main(String[] args) {
		Path noNomalize = Paths.get("C:/Users/Administrator/./Downloads/../Button.txt");
		Path normalize = Paths.get("C:/Users/Administrator/./Downloads/../Button.txt").normalize();
		
		System.out.println(noNomalize);
		System.out.println(normalize); //중복 제거
	}

}
