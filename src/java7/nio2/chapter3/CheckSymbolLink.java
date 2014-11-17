package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckSymbolLink {

	public static void main(String[] args) {
		//�ɺ� ��ũ �˻��ϱ�
		Path link = FileSystems.getDefault().getPath("rafael.nadal.5");
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
		
		boolean linkIsSymbolicLink = Files.isSymbolicLink(link);
		boolean targetIsSymbolicLink = Files.isSymbolicLink(target);
		
		System.out.println(link.toString() + "��ũ�� �ɺ� ��ũ�� ���� �ϳ� ? --> " + linkIsSymbolicLink);
		System.out.println(target.toString() + "Ÿ�ٿ� �ɺ� ��ũ�� ���� �ϳ� ? --> " + targetIsSymbolicLink);
				
		try {
			Boolean linkIsSymbolicLink2 = (Boolean)Files.getAttribute(link, "basic:isSymbolicLink");
			Boolean targetIsSymbolicLink2 = (Boolean)Files.getAttribute(target, "basic:isSymbolicLink");
			
			System.out.println(link.toString() + "��ũ�� �ɺ� ��ũ�� ���� �ϳ� ?  ==> " + linkIsSymbolicLink2);
			System.out.println(target.toString() + "Ÿ�ٿ� �ɺ� ��ũ�� ���� �ϳ� ? ==> " + targetIsSymbolicLink2);
			
		}catch (IOException | UnsupportedOperationException | SecurityException e) {
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
	}
	

}
