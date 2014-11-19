package java7.nio2.chapter4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory24 {

	public static void main(String[] args) {
		//deleteOnExit() �޼���� �ӽ� ���͸� ���� �ϱ�
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpDirPrefix = "rafa_";
		
		try {
			//���ξ� ���� �ӽ� ���͸� ����
			Path tmpDir = Files.createTempDirectory(basedir, tmpDirPrefix);
			System.out.println("�ӽ� ���͸� ���� : " + tmpDir);
			//deleteOnExit()�� Path�� �ƴ� File Ŭ������ �޼��� �̱� ������  Path.toFile() �޼��带 ȣ���ؼ� Path�� File�� ��ȯ �Ͽ��� �Ѵ�.
			
			System.out.println("*** �ӽ� ���͸� ���� �� ***");
			
			Thread.sleep(5000);
			File asFile = tmpDir.toFile(); 
			asFile.deleteOnExit();
			
			System.out.println("*** �ӽ� ���͸� ���� �Ϸ�!! ***");
			
			
			
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
		
		
	}

}
