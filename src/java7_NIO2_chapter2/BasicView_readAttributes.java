package java7_NIO2_chapter2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class BasicView_readAttributes {

	public static void main(String[] args) {
		BasicFileAttributes attr = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		try {
			attr = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("���� ũ�� : " + attr.size());
		System.out.println("���� ���� �ð� : " + attr.creationTime());
		System.out.println("�ֱ� ���� �ð� : " + attr.lastAccessTime());
		System.out.println("�ֱ� ���� �ð� : " + attr.lastModifiedTime());
		
		System.out.println("���丮�ΰ� ? : " + attr.isDirectory());
		System.out.println("�Ϲ� �����ΰ� ? : " + attr.isRegularFile());
		System.out.println("�ɺ� ��ũ�ΰ� ? : " + attr.isSymbolicLink());
		System.out.println("�� ���� ���ΰ� ? : " + attr.isOther());
	}

}
