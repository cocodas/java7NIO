package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class SymbolLink01 {

	public static void main(String[] args) {
		//심볼 링크 생성 하기
		Path link = FileSystems.getDefault().getPath("rafael.nadal.1");//getPath(path);
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.txt");
		
		try {
			Files.createSymbolicLink(link, target);
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("권한 거부 !");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("지원하지 않는 작업 발견!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O 에러 발생");
			}
			System.err.println(e);
		}
	}

}
