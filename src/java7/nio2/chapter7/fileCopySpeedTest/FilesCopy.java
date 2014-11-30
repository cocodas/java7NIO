package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FilesCopy extends BasicSetting{

	public void fileCopy(Path copyFrom, Path copyTo) {
		deleteCopied(copyTo);
		System.out.println("***** Files클래스의 copy() 메서드를 사용 하여 파일 복사 *****");		
		
		try {
			startTime = System.nanoTime();
			
			Files.copy(copyFrom, copyTo, NOFOLLOW_LINKS);
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("File클래스의 copy() 메서드 경과 시간 : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
