package java7.nio2.chapter3;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class IsSameFile {

	public static void main(String[] args) {
		//링크와 대상이 같은 파일을 가리키는지 검사하기
		Path link = FileSystems.getDefault().getPath("rafeal.nadal.7");
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
			boolean linkedpath = Files.isSameFile(link, target);
			//System.out.println(linkedpath);
			if (linkedpath == true) {
				System.out.println(link.toString() + " 와 " + target.toString() + "은 같은 파일을 가르킨다.");
			}else {
				System.out.println(link.toString() + " 와 " + target.toString() + "은 같은 파일을 가르키지 않는다.");
			}
		} catch (IOException e2) {
			System.err.println(e2);
		}
		
//		try {
//			Path linkedpath = Files.readSymbolicLink(link);
//			System.out.println(linkedpath.toString());
//		} catch (Exception e2) {
//			System.err.println(e2);
//		}
		
	}

}
