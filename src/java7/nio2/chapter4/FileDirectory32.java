package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//static import : enum 타입은 애플리케이션에 임포트할 수 있다. enum 타입의 필드 뿐 아니라 정적 필드나 메서드를 임포트 할수 있다.(직접 입력)
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;;

public class FileDirectory32 {

	public static void main(String[] args) {
		//두 경로 사이에 복사 하기
		Path copyFrom = Paths.get(System.getProperty("user.home"), "Downloads", "nature.jpg");
		Path copyTo = Paths.get(System.getProperty("user.home"), "javaTest", copyFrom.getFileName().toString());
		
		try {
			Files.copy(copyFrom, copyTo, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
