package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadSymbolicLink {

	public static void main(String[] args) {
		//��ũ�� ��� �˾Ƴ���
		Path link = FileSystems.getDefault().getPath("rafeal.nadal.5");
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
			Path linkedpath = Files.readSymbolicLink(link);
			System.out.println(linkedpath.toString());
		} catch (IOException e2) {
			System.err.println(e2);
		}
	}

}
