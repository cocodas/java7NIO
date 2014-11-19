package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory13 {

	public static void main(String[] args) {
		//readAllBytes()�� ��� �Ͽ� �ؽ�Ʈ ���� �б�
		Path wikiPath = Paths.get(System.getProperty("user.home"), "Downloads", "wiki.txt");
		
		try {
			byte[] wikiArray = Files.readAllBytes(wikiPath);
			String wikiString = new String(wikiArray, "UTF-8");
			System.out.println(wikiString);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
