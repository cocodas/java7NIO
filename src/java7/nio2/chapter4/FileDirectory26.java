package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory26 {

	public static void main(String[] args) {
		//�⺻ ���͸��� �����Ͽ� �ӽ� ���� �����
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			//������ ���丮 ���ξ� ���̾� 3�� ��ȯ
			Path tmp = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("TMP : " + tmp.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
