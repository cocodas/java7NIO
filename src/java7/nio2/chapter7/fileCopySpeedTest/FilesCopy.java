package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FilesCopy extends BasicSetting{

	public void fileCopy(Path copyFrom, Path copyTo) {
		deleteCopied(copyTo);
		System.out.println("***** FilesŬ������ copy() �޼��带 ��� �Ͽ� ���� ���� *****");		
		
		try {
			startTime = System.nanoTime();
			
			Files.copy(copyFrom, copyTo, NOFOLLOW_LINKS);
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("FileŬ������ copy() �޼��� ��� �ð� : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
