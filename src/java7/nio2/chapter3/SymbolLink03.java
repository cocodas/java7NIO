package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class SymbolLink03 {

	public static void main(String[] args) {
		//setAttribute() 메서드를 사용해 링크 속성 수정 하기
		Path link = FileSystems.getDefault().getPath("rafael.nadal.3");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.createSymbolicLink(link, target);
			
			FileTime lm = (FileTime)Files.getAttribute(target, "basic:lastModifiedTime", NOFOLLOW_LINKS);
			FileTime la = (FileTime)Files.getAttribute(target, "basic:lastAccessTime", NOFOLLOW_LINKS);
			//System.out.println(la.toString());
			//FileTime class : asDaysAndNanos(), from(), fromMillis(), to(), toMillis(), equals(), hashCode(), compareTo(), toString() 
			
			Files.setAttribute(link, "basic:lastModifiedTime", lm, NOFOLLOW_LINKS);
			//setAttribute(Path, 속성, Object 값, 링크 옵션);
			Files.setAttribute(link, "basic:lastAccessTime", la, NOFOLLOW_LINKS);
			
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
	}

}
