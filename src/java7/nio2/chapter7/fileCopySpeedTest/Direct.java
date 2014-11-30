package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Direct extends BasicSetting {
	
	public void direct(Path copyFrom, Path copyTo) {
		deleteCopied(copyTo);
		System.out.println("***** 다이렉트 버퍼를 사용 하여 파일 복사 *****");
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			startTime = System.nanoTime();
			
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
			int bytesCount;
			while ((bytesCount = fileChannelFrom.read(byteBuffer)) > 0) {
				byteBuffer.flip();
				
				fileChannelTo.write(byteBuffer);
				
				byteBuffer.clear();
			}
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("다이렉트 버퍼 경과 시간 : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
			
		} catch (IOException e) {
			System.err.println(e);
		}		
	}
}
