package java7.nio2.chapter4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory28 {

	public static void main(String[] args) {
		//deleteOnExit() �޼���� �ӽ� ���� ���� �ϱ�
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			final Path tmpFile = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("���� �̸� : " + tmpFile.toString() + " ����!!");
			
			System.out.println("*** �ӽ� ���� ���� �� ***");
			Thread.sleep(5000);
			
			File asFile = tmpFile.toFile();
			asFile.deleteOnExit();
			
			System.out.println("*** �ӽ� ���� ���� �Ϸ�!! ***");
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
