package java7.nio2.chapter7.seekableByteChannel02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;


public class SeekableByteChannelMain2 {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "story.txt" );
		
		//SeekableByteChannel을 사용해서 파일을 쓴다.
		try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING))){
			
			ByteBuffer buffer = ByteBuffer.wrap("Study Hard!!!".getBytes());
			
			int write = seekableByteChannel.write(buffer);
			System.out.println("쓰여진 바이트의 수 : " + write);
			
			buffer.clear();
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
