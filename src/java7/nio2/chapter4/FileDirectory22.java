package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory22 {

	public static void main(String[] args) {
		//임시디렉터리가 생성될 기본 디렉터리를 지정
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpDirPrefix = "rafa_";
		
		try {
			Path tmp = Files.createTempDirectory(basedir, tmpDirPrefix);
			System.out.println("TMP : " + tmp.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
