package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory26 {

	public static void main(String[] args) {
		//기본 디렉터리를 지정하여 임시 파일 만들기
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			//지정된 디렉토리 접두어 접미어 3개 반환
			Path tmp = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("TMP : " + tmp.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
