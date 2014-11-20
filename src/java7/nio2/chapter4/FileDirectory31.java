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
		//deleteIfExists() �޼��带 ����Ͽ� ���� ���� �ϱ�
		Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		//������ ���� �Ѵ�
		try {
			boolean success = Files.deleteIfExists(path);
			System.out.println("���� ��Ȳ : " + success);
		} catch (IOException | SecurityException e) {
			
			System.err.println(e);
		}
	}

}
