package java7.nio2.chapter4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory35 {

	public static void main(String[] args) {
		//파일을 출력 스트림으로 복사 하기
		Path copyFrom = Paths.get(System.getProperty("user.home"),"Downloads", "ParkShinHye.jpg");
		Path copyTo = Paths.get(System.getProperty("user.home"), "javaTest", "ParkShinHy7.jpg");
		
		try (OutputStream outputStream = new FileOutputStream(copyTo.toFile())){
			
			Files.copy(copyFrom, outputStream);
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
