package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory31 {

	public static void main(String[] args) throws DirectoryNotEmptyException {
		//deleteIfExists() 메서드를 사용하여 파일 삭제 하기
		Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		//파일을 삭제 한다
		try {
			boolean success = Files.deleteIfExists(path);
			System.out.println("삭제 상황 : " + success);
		} catch (IOException | SecurityException e) {
			
			System.err.println(e);
		}
	}

}
