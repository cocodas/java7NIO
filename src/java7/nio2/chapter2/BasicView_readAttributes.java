package java7.nio2.chapter2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class BasicView_readAttributes {

	public static void main(String[] args) {
		BasicFileAttributes attr = null;
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		try {
			attr = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("파일 크기 : " + attr.size());
		System.out.println("파일 생성 시간 : " + attr.creationTime());
		System.out.println("최근 접근 시간 : " + attr.lastAccessTime());
		System.out.println("최근 수정 시간 : " + attr.lastModifiedTime());
		
		System.out.println("디렉토리인가 ? : " + attr.isDirectory());
		System.out.println("일반 파일인가 ? : " + attr.isRegularFile());
		System.out.println("심볼 링크인가 ? : " + attr.isSymbolicLink());
		System.out.println("그 외의 것인가 ? : " + attr.isOther());
		System.out.println("fileKey ? : " + attr.fileKey());
	}

}
