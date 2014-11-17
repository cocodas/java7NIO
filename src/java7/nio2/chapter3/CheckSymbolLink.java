package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckSymbolLink {

	public static void main(String[] args) {
		//심볼 링크 검사하기
		Path link = FileSystems.getDefault().getPath("rafael.nadal.5");
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
		
		boolean linkIsSymbolicLink = Files.isSymbolicLink(link);
		boolean targetIsSymbolicLink = Files.isSymbolicLink(target);
		
		System.out.println(link.toString() + "링크에 심볼 링크가 존재 하나 ? --> " + linkIsSymbolicLink);
		System.out.println(target.toString() + "타겟에 심볼 링크가 존재 하나 ? --> " + targetIsSymbolicLink);
				
		try {
			Boolean linkIsSymbolicLink2 = (Boolean)Files.getAttribute(link, "basic:isSymbolicLink");
			Boolean targetIsSymbolicLink2 = (Boolean)Files.getAttribute(target, "basic:isSymbolicLink");
			
			System.out.println(link.toString() + "링크에 심볼 링크가 존재 하나 ?  ==> " + linkIsSymbolicLink2);
			System.out.println(target.toString() + "타겟에 심볼 링크가 존재 하나 ? ==> " + targetIsSymbolicLink2);
			
		}catch (IOException | UnsupportedOperationException | SecurityException e) {
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
	}
	

}
