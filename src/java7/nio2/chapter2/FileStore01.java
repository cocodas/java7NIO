package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileStore01 {

	public static void main(String[] args) {
		
		//��� ���� ������� �Ӽ� ��������.
		FileSystem fs = FileSystems.getDefault();
		for (FileStore store : fs.getFileStores()) {
			try {
				long totalSpace = store.getTotalSpace() / 1024 ;
				long usedSpace = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
				long availableSpace = store.getUsableSpace() / 1024;
				boolean isReadOnly = store.isReadOnly();
				
				System.out.println("--- " + store.name() + " --- " + store.type());
				System.out.println("��ü ���� : " + totalSpace);
				System.out.println("��� ���� : " + usedSpace);
				System.out.println("��� ���� ���� : " + availableSpace);
				System.out.println("�б� ���� �Դϱ�? " + isReadOnly);
				
			} catch (IOException e) { 
				System.err.println(e);
			}
		}

	}

}
