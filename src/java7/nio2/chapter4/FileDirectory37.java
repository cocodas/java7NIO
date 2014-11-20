package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileDirectory37 {

	public static void main(String[] args) {
		//move()�� resolveSibling() ��� �Ͽ� ���� �̸� �ٲٱ�
		Path moveFrom = Paths.get(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.move(moveFrom, moveFrom.resolveSibling("ReName Nature.jpg"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
