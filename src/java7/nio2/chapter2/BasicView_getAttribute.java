package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicView_getAttribute {

	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		//getAttribute 메서드 : 단일 속성을 알아내고 싶을때.
		try {
			long size = (Long)Files.getAttribute(path, "basic:size");
			System.out.println("Size : " + size);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
