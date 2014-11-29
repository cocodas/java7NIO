package java7.nio2.chapter7.fileChannel02;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class FileLockSystemTestMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "email", "cocodas.txt");
		ByteBuffer buffer = ByteBuffer.wrap("Access the cocodas File!!!".getBytes());
		
		try {
			FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.READ));
			
			fileChannel.position(0);
			fileChannel.write(buffer);
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}

}
