package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory03 {

	public static void main(String[] args) {
		//여러 경로가 같은 파일을 가르키는지 검사(절대 경로와 다양한 상대 경로가 같은 파일을 가르키는지 검사 ) isSameFile()
		Path path1 = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "Button.txt");
		Path path2 = FileSystems.getDefault().getPath("C:/Users/Administrator/Downloads/Button.txt");
		Path path3 = FileSystems.getDefault().getPath("C:/Users/Administrator/DownLoads", "Button.txt");
		
		try {
			boolean isSameFile12 = Files.isSameFile(path1, path2);
			boolean isSameFile13 = Files.isSameFile(path1, path3);
			boolean isSameFile23 = Files.isSameFile(path2, path3);
			
			System.out.println("경로1과 경로2는 서로 같은 파일을 가르키나? " + isSameFile12);
			System.out.println("경로1과 경로3는 서로 같은 파일을 가르키나? " + isSameFile13);
			System.out.println("경로2와 경로3는 서로 같은 파일을 가르키나? " + isSameFile23);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//파일 가시성 검사하기 isHidden()
		
		try {
			boolean isHidden = Files.isHidden(path1);
			System.out.println("숨김 파일인가요? " + isHidden);
		} catch (IOException e2) {
			System.err.println(e2);
		}
	}
}
