package java7.nio2.chapter7.seekableByteChannel05;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;


public class Seekable {
	ByteBuffer copy = ByteBuffer.allocate(620);
	
	public void seekableByteChannel(Path path) {
		copy.put("\n".getBytes());
		
		try {
			SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE));
			
			int nbytes;
			do {
				nbytes = seekableByteChannel.read(copy);				
						
			} while (nbytes != -1 && copy.hasRemaining());
			
			copy.flip();
			
			seekableByteChannel.position(seekableByteChannel.size());
			
			while (copy.hasRemaining()) {
				seekableByteChannel.write(copy);
			}
			
			copy.clear();
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
