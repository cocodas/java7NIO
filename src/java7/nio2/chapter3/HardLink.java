package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class HardLink {

	public static void main(String[] args) {
		//하드 링크 생성하기
		Path link = FileSystems.getDefault().getPath("rafeal.nadal.4");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.createLink(link, target); //하드 링크 생성
				System.out.println("성공적으로 링크를 생성 하였습니다! ");
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("접근 권한이 없습니다.!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("직업을 지원 하지 않습니다.!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O 에러 발생!");
			}
			
			System.err.println(e);
		}
	}

}
