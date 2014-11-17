package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserDefinedFileAttributeView05 {

	public static void main(String[] args) {
		//������ ����� ���� �Ӽ� ���� �ϱ�
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "Button.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		
		try {
			udfav.delete("file.description");
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
