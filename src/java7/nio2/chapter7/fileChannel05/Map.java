package java7.nio2.chapter7.fileChannel05;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Map {
	
	public void usingMap(Path copyFrom, Path copyTo) {
		System.out.println("FileChannel Ŭ������ map() �޼ҵ带 ��� �Ͽ� ������ ���� �ϰڽ��ϴ�!!");
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			MappedByteBuffer buffer = fileChannelFrom.map(FileChannel.MapMode.READ_ONLY, 0, fileChannelFrom.size());
			
			fileChannelTo.write(buffer);
			buffer.clear();
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
