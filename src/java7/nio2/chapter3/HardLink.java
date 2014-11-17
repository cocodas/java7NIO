package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class HardLink {

	public static void main(String[] args) {
		//�ϵ� ��ũ �����ϱ�
		Path link = FileSystems.getDefault().getPath("rafeal.nadal.4");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.createLink(link, target); //�ϵ� ��ũ ����
				System.out.println("���������� ��ũ�� ���� �Ͽ����ϴ�! ");
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("���� ������ �����ϴ�.!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("������ ���� ���� �ʽ��ϴ�.!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O ���� �߻�!");
			}
			
			System.err.println(e);
		}
	}

}
