package java7_NIO2_chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

public class DOSView {

	public static void main(String[] args) {
		DosFileAttributes attr = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		try {
			attr = Files.readAttributes(path, DosFileAttributes.class);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		System.out.println("읽기 전용인가? : " + attr.isReadOnly());
		System.out.println("숨김 파일인가? : " + attr.isHidden());
		System.out.println("archive인가? : " + attr.isArchive());
		System.out.println("운영체제 파일인가? : " + attr.isSystem());
		
	}

}
