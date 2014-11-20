package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory25 {

	public static void main(String[] args) {
		//임시 파일 생성 하기
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			//접두어 전미어에 null전달
			Path tmp1 = Files.createTempFile(null, null);
			System.out.println("TMP : " + tmp1.toString());
			
			//접두어 전미어 설정
			Path tmp2 = Files.createTempFile(tmpFilePrefix, tmpFileSufix);
			System.out.println("TMP : " + tmp2.toString());
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
