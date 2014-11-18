package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory03 {

	public static void main(String[] args) {
		//���� ��ΰ� ���� ������ ����Ű���� �˻�(���� ��ο� �پ��� ��� ��ΰ� ���� ������ ����Ű���� �˻� ) isSameFile()
		Path path1 = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "Button.txt");
		Path path2 = FileSystems.getDefault().getPath("C:/Users/Administrator/Downloads/Button.txt");
		Path path3 = FileSystems.getDefault().getPath("C:/Users/Administrator/DownLoads", "Button.txt");
		
		try {
			boolean isSameFile12 = Files.isSameFile(path1, path2);
			boolean isSameFile13 = Files.isSameFile(path1, path3);
			boolean isSameFile23 = Files.isSameFile(path2, path3);
			
			System.out.println("���1�� ���2�� ���� ���� ������ ����Ű��? " + isSameFile12);
			System.out.println("���1�� ���3�� ���� ���� ������ ����Ű��? " + isSameFile13);
			System.out.println("���2�� ���3�� ���� ���� ������ ����Ű��? " + isSameFile23);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//���� ���ü� �˻��ϱ� isHidden()
		
		try {
			boolean isHidden = Files.isHidden(path1);
			System.out.println("���� �����ΰ���? " + isHidden);
		} catch (IOException e2) {
			System.err.println(e2);
		}
	}
}
