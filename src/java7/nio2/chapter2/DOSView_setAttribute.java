package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class DOSView_setAttribute {

	public static void main(String[] args) {
		DosFileAttributes attr = null;
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		try {
			attr = Files.readAttributes(path, DosFileAttributes.class);
		} catch (IOException e1) {
			System.err.println(e1);
		}
		
		
		//hidden속성을 true로 설정
		try {
			Files.setAttribute(path, "dos:hidden", true, NOFOLLOW_LINKS);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
		//hidden 속성 가져오기
		try {
			boolean hidden = (Boolean)Files.getAttribute(path, "dos:hidden", NOFOLLOW_LINKS);
			System.out.println("숨김 파일인가? : " + hidden);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}

}
