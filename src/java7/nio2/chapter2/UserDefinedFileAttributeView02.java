package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserDefinedFileAttributeView02 {

	public static void main(String[] args) {
		//����� �Ӽ� ���� �ϱ�
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "Button.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		
		try {
			int written = udfav.write("file.description", Charset.defaultCharset().encode("�� ������ �������� ������ ���ԵǾ� �ֽ��ϴ�."));
			//CharSet class : atBugLevel(), checkName(), CharsetProvider(), Iterator providers()......

		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
