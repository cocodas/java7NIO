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
		System.out.println("***** FilesŬ������ copy(InputStream to Path) �޼��带 ��� �Ͽ� ���� ���� *****");	
		
		try {
			InputStream is = new FileInputStream(copyFrom.toFile());
			
			startTime = System.nanoTime();
			
			Files.copy(is, copyTo);
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("FileŬ������ copy(InputStram to Path) �޼��� ��� �ð� : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
