package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDirectory29 {

	public static void main(String[] args) {
		//DELETE_ON_CLOSE 옵션으로 임시 파일 삭제 하기
		Path basedir = Paths.get(System.getProperty("user.home"), "Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		Path tmpFile = null;
		
		try {
			tmpFile = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("파일 이름 : " + tmpFile.toString() + " 생성!!");
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (OutputStream outputStream = Files.newOutputStream(tmpFile, StandardOpenOption.DELETE_ON_CLOSE);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
			System.out.println("*** 임시파일 삭제 중 ***");
			Thread.sleep(5000);//5초 동안 대기 하는 방법으로 임시 파일의 I/O 처리를 시뮬레이션
			System.out.println("*** 임시파일 삭제 완료!!! ***");
					
			
		}catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
	}
}
