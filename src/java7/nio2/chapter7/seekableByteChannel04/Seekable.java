package java7.nio2.chapter7.seekableByteChannel04;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Seekable {
	
	ByteBuffer buffer1 = ByteBuffer.wrap("I love that movie director".getBytes());
	ByteBuffer buffer2 = ByteBuffer.wrap("Second".getBytes());
	
	public void seekableByteChanell(Path path) {
		try {
			SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE));
			
			//������ ���� �ؽ�Ʈ�� �߰� �Ѵ�.
			seekableByteChannel.position(seekableByteChannel.size());
			
			while (buffer1.hasRemaining()) {

				seekableByteChannel.write(buffer1);
			}
			
			//"first"�� "Second"�� �ٲ۴�.
			seekableByteChannel.position(416);
			while (buffer2.hasRemaining()) {
				seekableByteChannel.write(buffer2);
			}
			
			buffer1.clear();
			buffer2.clear();
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
