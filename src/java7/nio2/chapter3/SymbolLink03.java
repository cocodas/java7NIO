package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class SymbolLink03 {

	public static void main(String[] args) {
		//setAttribute() �޼��带 ����� ��ũ �Ӽ� ���� �ϱ�
		Path link = FileSystems.getDefault().getPath("rafael.nadal.3");
		Path target = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "nature.jpg");
		
		try {
			Files.createSymbolicLink(link, target);
			
			FileTime lm = (FileTime)Files.getAttribute(target, "basic:lastModifiedTime", NOFOLLOW_LINKS);
			FileTime la = (FileTime)Files.getAttribute(target, "basic:lastAccessTime", NOFOLLOW_LINKS);
			//System.out.println(la.toString());
			//FileTime class : asDaysAndNanos(), from(), fromMillis(), to(), toMillis(), equals(), hashCode(), compareTo(), toString() 
			
			Files.setAttribute(link, "basic:lastModifiedTime", lm, NOFOLLOW_LINKS);
			//setAttribute(Path, �Ӽ�, Object ��, ��ũ �ɼ�);
			Files.setAttribute(link, "basic:lastAccessTime", la, NOFOLLOW_LINKS);
			
		} catch (IOException | UnsupportedOperationException | SecurityException e) {
			if (e instanceof SecurityException) {
				System.err.println("���� ������ �����ϴ�.!");
			}
			if (e instanceof UnsupportedOperationException) {
				System.err.println("���� ���� �ʴ� �۾� �߰�!");
			}
			if (e instanceof IOException) {
				System.err.println("I/O ���� �߻�!");
			}
			System.err.println(e);
		}
	}

}
