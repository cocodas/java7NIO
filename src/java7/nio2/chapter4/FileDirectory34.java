package java7.nio2.chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory34 {

	public static void main(String[] args) {
		//���ͳ� URL���� �Է� ��Ʈ�� ���� ����
		Path copyTo = Paths.get(System.getProperty("user.home"), "javaTest", "sujiJJang.jpg");
		
		URI u = URI.create("http://imgnews.naver.net/image/022/2014/11/10/20141110002454_0_99_20141110155128.jpg");
		
		try (InputStream inputStream = u.toURL().openStream()){
			
			Files.copy(inputStream, copyTo);
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
