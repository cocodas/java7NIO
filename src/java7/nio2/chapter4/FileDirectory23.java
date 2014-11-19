package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory23 {

	public static void main(String[] args) {
		//���� ��ũ�� �ӽ� ���͸� ���� �ϱ�
		final Path basedir = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "tmp");
		final String tmpDirPrefix = "rafa_";
		
		try {
			//������ ���͸�(basedir)�� �ӽ� ���͸�(tmpDir)�� ���� �Ѵ�.
			final Path tmpDir = Files.createTempDirectory(basedir, tmpDirPrefix);
			System.out.println("�ӽ� ���͸� ���� : " + tmpDir);
			
			Runtime.getRuntime().addShutdownHook(new Thread(){
				public void run() {
					System.out.println("�ӽ� ���͸� ������ ������...");
					try {
						//10�ʵ��� ��� �ϴ� ������� �ӽ� ������ I/O ó���� �ùķ��̼�
						//�ð��� ���� �Ǹ� �ӽ� ������ �����ȴ�.
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						System.err.println(e1);
					} //�۾� �Ϸ�
					
					try (DirectoryStream<Path> ds = Files.newDirectoryStream(tmpDir)){
						for (Path file : ds) {
							Files.delete(file);//tmp���� �ȿ� �ִ� �ӽ� ���͸� ����
						}
						
						Files.delete(tmpDir); //�ӽ� ���͸� ���� �� ���� ���� �ӽ� ���͸��� ���� �ϸ� ���� �Ҽ� ���� ������
						
					} catch (IOException e) {
						System.err.println(e);
					}
					
					System.out.println("Shutdown-hook �Ϸ�...");
				}
			});
			
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

