package java7.nio2.chapter7.fileCopySpeedTest;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Map extends BasicSetting {
	
	public void map(Path copyFrom, Path copyTo) {
		deleteCopied(copyTo);
		System.out.println("***** Map 메서드를 사용 하여 파일 복사 *****");

		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			startTime = System.nanoTime();
			
			MappedByteBuffer mappedByteBuffer = fileChannelFrom.map(FileChannel.MapMode.READ_ONLY, 0, fileChannelFrom.size());
			
			fileChannelTo.write(mappedByteBuffer);
			mappedByteBuffer.clear();
			
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Map 메서드 경과 시간 : " + "[" +(elapsedTime/1000000000.0) +"]seconds");
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
