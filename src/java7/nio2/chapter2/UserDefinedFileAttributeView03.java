package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserDefinedFileAttributeView03 {

	public static void main(String[] args) {
		//����� ���� �Ӽ� �̸��� �� ũ�� �����ϱ�
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "Button.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		
		try {
			for (String name : udfav.list()) {
				System.out.println(udfav.size(name) + "    " + name);
				
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
