package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class BasicView_attributeUpdate {
	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		long time = System.currentTimeMillis();
		FileTime fileTime = FileTime.fromMillis(time);
		
		
		//setTime();
		try {
			Files.getFileAttributeView(path, BasicFileAttributeView.class).setTimes(fileTime, fileTime, fileTime);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//setLastModifiedTime();
		try {
			Files.setLastModifiedTime(path, fileTime);
		} catch (IOException e) {
			System.err.println(e);
		}

		//setAttribute();
		try {
			Files.setAttribute(path, "basic:lastModifiedTime", fileTime, NOFOLLOW_LINKS);
			Files.setAttribute(path, "basic:creationTime", fileTime, NOFOLLOW_LINKS);
			Files.setAttribute(path, "basic:lastAccessTime", fileTime, NOFOLLOW_LINKS);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//getAttribute()
		try {
			FileTime lastMdifiedTime = (FileTime)Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS);
			FileTime creationTime = (FileTime)Files.getAttribute(path, "basic:creationTime", NOFOLLOW_LINKS);
			FileTime lastAccessTime = (FileTime)Files.getAttribute(path, "basic:lastAccessTime", NOFOLLOW_LINKS);
			
			System.out.println("최근 수정 시간 : " + lastMdifiedTime);
			System.out.println("최근 생성 시간 : " + creationTime);
			System.out.println("최근 접근 시간 : " + lastAccessTime);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
