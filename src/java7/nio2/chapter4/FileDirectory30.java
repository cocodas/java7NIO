package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileDirectory30 {

	public static void main(String[] args) {
		//createTempFile() 메서드를 호출 하지 않고 임시 파일 시뮬레이션
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		Path tmpFile = null;
		
		tmpFile = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "tmp", tmpFilePrefix + "temporary" + tmpFileSufix);
		System.out.println("파일 이름 : " + tmpFile.toString() + " 생성!!");
		
		System.out.println("*** 임시 파일 삭제 중...***");
		
		try (OutputStream outputStream = Files.newOutputStream(tmpFile, StandardOpenOption.CREATE, StandardOpenOption.DELETE_ON_CLOSE);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
		
		Thread.sleep(5000);
		System.out.println("*** 임시 파일 삭제 완료 !!! ***");
			
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		
	}

}
