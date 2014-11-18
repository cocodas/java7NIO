package java7.nio2.chapter4;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileDirectory01 {

	public static void main(String[] args) {
		//파일이나 디렉터리가 있는지 검사 하기
		Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		boolean pathExists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
		System.out.println(pathExists);
		
		boolean notExists = Files.notExists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
		System.out.println(notExists);
		
		
		
	}

}
