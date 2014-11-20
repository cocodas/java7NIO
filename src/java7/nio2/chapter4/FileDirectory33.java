package java7.nio2.chapter4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileDirectory33 {

	public static void main(String[] args) {
		//입력 스트림을 파일로 복사 하기
		Path copyFrom = Paths.get(System.getProperty("user.home"), "Downloads", "Button.txt");
		Path copyTo = Paths.get(System.getProperty("user.home"), "javaTest", "Button.txt");
		
		try (InputStream inputStream = new FileInputStream(copyFrom.toFile())){
			
			Files.copy(inputStream, copyTo, REPLACE_EXISTING);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
