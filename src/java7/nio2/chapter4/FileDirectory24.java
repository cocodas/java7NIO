package java7.nio2.chapter4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory24 {

	public static void main(String[] args) {
		//deleteOnExit() 메서드로 임시 디렉터리 삭제 하기
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpDirPrefix = "rafa_";
		
		try {
			//접두어 붙힌 임시 디렉터리 생성
			Path tmpDir = Files.createTempDirectory(basedir, tmpDirPrefix);
			System.out.println("임시 디렉터리 생성 : " + tmpDir);
			//deleteOnExit()는 Path가 아닌 File 클래스의 메서드 이기 때문에  Path.toFile() 메서드를 호출해서 Path를 File로 변환 하여야 한다.
			
			System.out.println("*** 임시 디렉터리 삭제 중 ***");
			
			Thread.sleep(5000);
			File asFile = tmpDir.toFile(); 
			asFile.deleteOnExit();
			
			System.out.println("*** 임시 디렉터리 삭제 완료!! ***");
			
			
			
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
		
		
	}

}
