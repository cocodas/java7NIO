package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class TransferTo extends BasicSetting{

	public void transferTo(Path copyFrom, Path copyTo) {
		
		deleteCopied(copyTo);
		System.out.println("***** TransferTo 메서드를 사용 하여 파일 복사 *****");
		
		
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			startTime = System.nanoTime();
			
			fileChannelFrom.transferTo(0L, fileChannelFrom.size(), fileChannelTo);
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("TrasferTo 메서드 경과 시간 : " + "[" +(elapsedTime/1000000000.0) +"]seconds");

		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
}
