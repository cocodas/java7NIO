package java7.nio2.chapter7.seekableByteChannel06;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Seekable {
	
	ByteBuffer buffer = ByteBuffer.wrap("Here Point!! Sentence Start!!!".getBytes());
	
	public void seekableByteChannel(Path path) {
		try {
			SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE));
			
			seekableByteChannel.truncate(5000);
			
			seekableByteChannel.position(seekableByteChannel.size()-1);
			
			while (buffer.hasRemaining()) {

				seekableByteChannel.write(buffer);
				
			}
			
			buffer.clear();
			
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
