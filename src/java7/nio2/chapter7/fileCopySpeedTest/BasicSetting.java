package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BasicSetting  {
	long startTime, elapsedTime;
	int bufferSizeKB = 4;
	int bufferSize = bufferSizeKB * 1024;
	
	public void deleteCopied(Path path) {
		
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
				System.err.println(e);
		}
	}
}
