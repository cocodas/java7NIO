package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class UnbufferedStream extends BasicSetting{

	public void unbufferedStream(Path copyFrom, Path copyTo) {
		
		deleteCopied(copyTo);
		System.out.println("***** un-buffered streams�� byte �迭�� ��� �Ͽ� ���� ���� *****");
		
		File inFileStr = copyFrom.toFile();
		File outFileStr = copyTo.toFile();
		
		try {
			FileInputStream in = new FileInputStream(inFileStr);
			FileOutputStream out = new FileOutputStream(outFileStr);
			
			startTime = System.nanoTime();
			
			byte[] byteArray = new byte[bufferSize];
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				out.write(byteArray);
			}
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("un-buffered streams & byte Array ��� �ð� : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
}
