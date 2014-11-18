package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory07 {
	public static void main(String[] args) {
		//글롭 패턴을 적용해 내용 나열하기  글롭 : 특정 패턴과 매칭을 수행하는 동작을 뜻한다.
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		
		System.out.println("\nGlob pattern applied : ");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.{png,jpg,bmp,pdf,txt}")){
			//DirectoryStream interface : Filter<T>, boolean accept()
			for (Path file : ds) {
				System.out.println(file.getFileName());
				
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
