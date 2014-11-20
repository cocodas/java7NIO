package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//static import : enum Ÿ���� ���ø����̼ǿ� ����Ʈ�� �� �ִ�. enum Ÿ���� �ʵ� �� �ƴ϶� ���� �ʵ峪 �޼��带 ����Ʈ �Ҽ� �ִ�.(���� �Է�)
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;;

public class FileDirectory32 {

	public static void main(String[] args) {
		//�� ��� ���̿� ���� �ϱ�
		Path copyFrom = Paths.get(System.getProperty("user.home"), "Downloads", "nature.jpg");
		Path copyTo = Paths.get(System.getProperty("user.home"), "javaTest", copyFrom.getFileName().toString());
		
		try {
			Files.copy(copyFrom, copyTo, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
