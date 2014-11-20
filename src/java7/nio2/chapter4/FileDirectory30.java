package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileDirectory30 {

	public static void main(String[] args) {
		//createTempFile() �޼��带 ȣ�� ���� �ʰ� �ӽ� ���� �ùķ��̼�
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		Path tmpFile = null;
		
		tmpFile = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "tmp", tmpFilePrefix + "temporary" + tmpFileSufix);
		System.out.println("���� �̸� : " + tmpFile.toString() + " ����!!");
		
		System.out.println("*** �ӽ� ���� ���� ��...***");
		
		try (OutputStream outputStream = Files.newOutputStream(tmpFile, StandardOpenOption.CREATE, StandardOpenOption.DELETE_ON_CLOSE);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
		
		Thread.sleep(5000);
		System.out.println("*** �ӽ� ���� ���� �Ϸ� !!! ***");
			
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
	}

}
