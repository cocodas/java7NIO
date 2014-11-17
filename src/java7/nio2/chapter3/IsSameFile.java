package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class IsSameFile {

	public static void main(String[] args) {
		//��ũ�� ����� ���� ������ ����Ű���� �˻��ϱ�
		Path link = FileSystems.getDefault().getPath("rafeal.nadal.7");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.createSymbolicLink(link, target);
			
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("���� ������ �����ϴ�.!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("���� ���� �ʴ� �۾� �߰�!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O ���� �߻�!");
			}
			System.err.println(e);
		}
		
		try {
			boolean linkedpath = Files.isSameFile(link, target);
			//System.out.println(linkedpath);
			if (linkedpath == true) {
				System.out.println(link.toString() + " �� " + target.toString() + "�� ���� ������ ����Ų��.");
			}else {
				System.out.println(link.toString() + " �� " + target.toString() + "�� ���� ������ ����Ű�� �ʴ´�.");
			}
		} catch (IOException e2) {
			System.err.println(e2);
		}
		
//		try {
//			Path linkedpath = Files.readSymbolicLink(link);
//			System.out.println(linkedpath.toString());
//		} catch (Exception e2) {
//			System.err.println(e2);
//		}
		
	}

}
