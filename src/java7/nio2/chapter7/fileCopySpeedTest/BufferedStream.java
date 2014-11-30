package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class BufferedStream extends BasicSetting{

	public void bufferedStream(Path copyFrom, Path copyTo) {
		deleteCopied(copyTo);
		System.out.println("***** buffered streams와 byte 배열을 사용 하여 파일 복사 *****");
		
		File inFileStr = copyFrom.toFile();
		File outFileStr = copyTo.toFile();
		
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr));
			
			startTime = System.nanoTime();
			
			byte[] byteArray = new byte[bufferSize];
			
			int bytesCount;
			
			while ((bytesCount = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, bytesCount);	
			}
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("buffered streams & byte Array 경과 시간 : " + "[" +(elapsedTime/1000000000.0) +"]seconds");

		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}
}
