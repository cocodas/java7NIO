package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory07 {
	public static void main(String[] args) {
		//�۷� ������ ������ ���� �����ϱ�  �۷� : Ư�� ���ϰ� ��Ī�� �����ϴ� ������ ���Ѵ�.
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
