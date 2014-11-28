package java7.nio2.chapter7.readableByteChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Readable {
	public void readableByteChannel(Path path) {
		//ReadableByteChannel을 사용해서 파일을 읽는다.
		try {
			ReadableByteChannel readableByteChannel = Files.newByteChannel(path);
			
			ByteBuffer buffer = ByteBuffer.allocate(100);
			buffer.clear();
			
			String encoding = System.getProperty("file.encoding");
			
			while (readableByteChannel.read(buffer) > 0) {
				buffer.flip();
				System.out.println(Charset.forName(encoding).decode(buffer));
				buffer.clear();
				
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
