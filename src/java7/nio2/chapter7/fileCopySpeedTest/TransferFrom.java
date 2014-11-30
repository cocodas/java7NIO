package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class TransferFrom extends BasicSetting{
	
	public void transferFrom(Path copyFrom, Path copyTo) {
		
		deleteCopied(copyTo);
		System.out.println("***** TransferFrom �޼��带 ��� �Ͽ� ���� ���� *****");
		
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			startTime = System.nanoTime();
			
			fileChannelTo.transferFrom(fileChannelFrom, 0L, (int)fileChannelFrom.size());
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("TrasferFrom �޼��� ��� �ð� : " + "[" +(elapsedTime/1000000000.0) +"]seconds");

		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
