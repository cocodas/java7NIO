package java7.nio2.chapter7.seekableByteChannel01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;


public class SeekableByteChannelMain {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.home"), "rafaelnadal", "grandslam", "RolandGarros", "story.txt" );

		try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ))){
			
			ByteBuffer buffer = ByteBuffer.allocate(12);
			String encoding = System.getProperty("file.encoding");
			buffer.clear();
			
			while (seekableByteChannel.read(buffer) > 0 ) {
				buffer.flip();
//				Charset charset = Charset.forName("UTF-8");
//				
//				try (BufferedReader bufferedReader = Files.newBufferedReader(path, charset)){
//					String line = null;
//					while ((line = bufferedReader.readLine()) != null) {
//						System.out.println(line);
//					}
//					
//				} catch (Exception e) {
//					System.err.println(e);
//				}
				System.out.println(Charset.forName(encoding).decode(buffer));
				buffer.clear();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
