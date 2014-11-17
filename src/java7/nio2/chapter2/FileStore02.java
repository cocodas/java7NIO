package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStore02 {

	public static void main(String[] args) {
		//������ �ִ� ���� ������� �Ӽ� ��������
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
				try {
					FileStore store = Files.getFileStore(path);
					
					long totalSpace = store.getTotalSpace() / 1024;
					long usedSpace = (store.getTotalSpace() - store.getUnallocatedSpace()) /1024;
					long availableSpace = store.getUsableSpace() / 1024;
					boolean isReadOnly = store.isReadOnly();
					
					System.out.println("--- " + store.name() + " --- " + store.type());
					System.out.println("��ü ���� : " + totalSpace);
					System.out.println("��� ���� : " + usedSpace);
					System.out.println("��� ������ ���� : " + availableSpace);
					System.out.println("�б� ���� �ΰ�? : " + isReadOnly);
				
				} catch (IOException e) {
					System.err.println(e);
				}
	}

}
