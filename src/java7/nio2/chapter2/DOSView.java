package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

public class DOSView {

	public static void main(String[] args) {
		DosFileAttributes attr = null;
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		try {
			attr = Files.readAttributes(path, DosFileAttributes.class);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		System.out.println("�б� �����ΰ�? : " + attr.isReadOnly());
		System.out.println("���� �����ΰ�? : " + attr.isHidden());
		System.out.println("archive�ΰ�? : " + attr.isArchive());
		System.out.println("�ü�� �����ΰ�? : " + attr.isSystem());
		System.out.println("ũ�� : " + attr.size());
		
	}

}
