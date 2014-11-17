package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadSymbolicLink {

	public static void main(String[] args) {
		//링크의 대상 알아내기
		Path link = FileSystems.getDefault().getPath("rafeal.nadal.5");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.createSymbolicLink(link, target);
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("접근 권한이 없습니다.!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("지원 되지 않는 작업 발견!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O 에러 발생!");
			}
			System.err.println(e);			
		}
		
		try {
			Path linkedpath = Files.readSymbolicLink(link);
			System.out.println(linkedpath.toString());
		} catch (IOException e2) {
			System.err.println(e2);
		}
	}

}
