package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class SymbolLink02 {

	public static void main(String[] args) {
		//링크 기본 속성 수정 (리눅스에서)
		Path link = FileSystems.getDefault().getPath("rafael.nadal.2");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			PosixFileAttributes attrs = Files.readAttributes(target, PosixFileAttributes.class);
			FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(attrs.permissions());
			
			Files.createSymbolicLink(link, target, attr);
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("권한이 없습니다.!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("지원 되지 않는 동작 발견!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O 에러 발생!");
			}
		}
		

	}

}
