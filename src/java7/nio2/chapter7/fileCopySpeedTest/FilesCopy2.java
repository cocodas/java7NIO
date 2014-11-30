package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesCopy2 extends BasicSetting{

	public void fileCopy2(Path copyFrom, Path copyTo) {
		deleteCopied(copyTo);
		System.out.println("***** Files클래스의 copy(InputStream to Path) 메서드를 사용 하여 파일 복사 *****");	
		
		try {
			InputStream is = new FileInputStream(copyFrom.toFile());
			
			startTime = System.nanoTime();
			
			Files.copy(is, copyTo);
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("File클래스의 copy(InputStram to Path) 메서드 경과 시간 : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
