package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FileDirectory05 {

	public static void main(String[] args) {
		//새 디렉토리 만들기
		Path newdir = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "Button.txt");
		
		//속성 세트 추가
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
		//PosixFilePermissions.fromString() : 코드에 직접 쓴 문자열(“rw-r--r--”)로 파일의 권한을 설정할 수 있다.
		
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		//PosixFilePermissions.asFileAttribute() : 파일 권한 Set를 받아서 FileAttribute를 생성한다. 그리고 Path.createFile()이나 Path.createDirectory()에 속성을 전달한다.
		
		try {
			Files.createDirectories(newdir, attr);
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
