package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.omg.SendingContext.RunTime;

public class FileDirectory27 {
	public static void main(String[] args) {
		//���� ��ũ�� �ӽ� ���� �����ϱ�
		
		Path basedir = Paths.get(System.getProperty("user.home"),"Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			final Path tmpFile = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("���� �̸� : " + tmpFile.toString() + " ����");
			
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					System.out.println("*** �ӽ� ���� ���� �� ***");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.err.println(e);				
					}
					try {
						Files.delete(tmpFile);
					} catch (IOException e1) {
						System.err.println(e1);
					}
					
					System.out.println("*** �ӽ� ���� ���� �Ϸ�!! ***");
					
				}
				
			});
		} catch (IOException e2) {
			System.err.println(e2);
		}
		
	}

}
