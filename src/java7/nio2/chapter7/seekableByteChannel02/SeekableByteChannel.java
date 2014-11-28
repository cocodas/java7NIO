package java7.nio2.chapter7.seekableByteChannel02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class SeekableByteChannel {
	
	public void seekableByteChannel(Path path) {
		try {
			java.nio.channels.SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING));
			ByteBuffer buffer = ByteBuffer.wrap("SeekableByteChannel Write!!!".getBytes());
			
			int write = seekableByteChannel.write(buffer);
			System.out.println("쓰여진 바이트의 수 : " + write);
			
			buffer.clear();
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
