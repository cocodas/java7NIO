package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDirectory29 {

	public static void main(String[] args) {
		//DELETE_ON_CLOSE �ɼ����� �ӽ� ���� ���� �ϱ�
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		Path tmpFile = null;
		
		try {
			tmpFile = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("���� �̸� : " + tmpFile.toString() + " ����!!");
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (OutputStream outputStream = Files.newOutputStream(tmpFile, StandardOpenOption.DELETE_ON_CLOSE);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
			System.out.println("*** �ӽ����� ���� �� ***");
			Thread.sleep(5000);//5�� ���� ��� �ϴ� ������� �ӽ� ������ I/O ó���� �ùķ��̼�
			System.out.println("*** �ӽ����� ���� �Ϸ�!!! ***");
					
			
		}catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
	}
}
