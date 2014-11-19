package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory21 {

	public static void main(String[] args) {
		//임시 디렉토리 생산 하기
		String tmpDirPrefix = "nio_";
		
		try {
			//null 접두어를 전달
			Path tmp1 = Files.createTempDirectory(null);
			System.out.println("TMP : " + tmp1.toString());
			
			//미리 정해놓은 접두어를 설정
			Path tmp2 = Files.createTempDirectory(tmpDirPrefix);
			System.out.println("TMP : " + tmp2.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//임시 디렉토리의 기본 위치가 어딘지 모른다면 다음 코드 사용
		String defaultTmp = System.getProperty("java.io.tmpdir");
		System.out.println(defaultTmp);
	}

}
