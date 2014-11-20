package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileDirectory36 {

	public static void main(String[] args) {
		//파일과 디렉터리 이동 하기
		Path moveFrom = Paths.get(System.getProperty("user.home"), "Downloads", "nature.jpg");
		Path moveToDir = Paths.get(System.getProperty("user.home"), "javaTest");
		
		//이름 지정할때
//		try {
//			Files.move(moveFrom, moveTo);
//		} catch (IOException e) {
//			System.err.println(e);
//		}
		
		//이름을 그대로 받아서 옮길때
		try {
			Files.move(moveFrom, moveToDir.resolve(moveFrom.getFileName()), StandardCopyOption.REPLACE_EXISTING);
			//resolve(moveFrom.getFileName())--> 이름을 받는 경로 조합.
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
