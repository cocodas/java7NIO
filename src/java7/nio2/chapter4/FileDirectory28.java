package java7.nio2.chapter4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory28 {

	public static void main(String[] args) {
		//deleteOnExit() 메서드로 임시 파일 삭제 하기
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			final Path tmpFile = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("파일 이름 : " + tmpFile.toString() + " 생성!!");
			
			System.out.println("*** 임시 파일 삭제 중 ***");
			Thread.sleep(5000);
			
			File asFile = tmpFile.toFile();
			asFile.deleteOnExit();
			
			System.out.println("*** 임시 파일 삭제 완료!! ***");
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
