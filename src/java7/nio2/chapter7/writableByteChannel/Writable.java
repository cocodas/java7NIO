package java7.nio2.chapter7.writableByteChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Writable {
	
	public void writableByteChannel(Path path) {
		//WritableByteChannel을 사용해서 파일에 쓴다.
		try {
			//여기서는 옵션을 지정해 줘야 한다. APPED -> 글 끝에 붙여 쓰기.
			WritableByteChannel writableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.APPEND));
			ByteBuffer buffer = ByteBuffer.wrap("\n rolendSunq Bro is babo".getBytes());
			
			int write = writableByteChannel.write(buffer);
			
			System.out.println("글 쓰는데 사용된 바이트 수 : " + write + " Byte");
			
			buffer.clear();
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
