package java7_NIO2_chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.security.acl.Owner;

public class PosixView01 {

	public static void main(String[] args) {
		PosixFileAttributes attr = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		try {
			attr = Files.readAttributes(path, PosixFileAttributes.class);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		System.out.println("���� ������ : " + attr.owner().getName());
		System.out.println("���� �׷� : " + attr.group().getName());
		System.out.println("���� ���� : " + attr.permissions().toString());
	}

}
