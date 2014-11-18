package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory06 {

	public static void main(String[] args) {
		//��ü ���� �����ϱ� newDirectiryStream()
		Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads");
		
		System.out.println("--- ���ʹ� ���� ���� �ʾҽ��ϴ�. --- ");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
			for (Path file : ds) {
				System.out.println("���� �̸� : " + file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
