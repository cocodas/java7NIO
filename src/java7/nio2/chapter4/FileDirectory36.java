package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileDirectory36 {

	public static void main(String[] args) {
		//���ϰ� ���͸� �̵� �ϱ�
		Path moveFrom = Paths.get(System.getProperty("user.home"), "Downloads", "nature.jpg");
		Path moveToDir = Paths.get(System.getProperty("user.home"), "javaTest");
		
		//�̸� �����Ҷ�
//		try {
//			Files.move(moveFrom, moveTo);
//		} catch (IOException e) {
//			System.err.println(e);
//		}
		
		//�̸��� �״�� �޾Ƽ� �ű涧
		try {
			Files.move(moveFrom, moveToDir.resolve(moveFrom.getFileName()), StandardCopyOption.REPLACE_EXISTING);
			//resolve(moveFrom.getFileName())--> �̸��� �޴� ��� ����.
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
