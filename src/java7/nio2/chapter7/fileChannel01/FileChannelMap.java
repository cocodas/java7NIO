package java7.nio2.chapter7.fileChannel01;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class FileChannelMap {
	
	MappedByteBuffer buffer = null;
	
	public void mapped(Path path) {
		try {
			FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ));
			//FileChannel로 캐스팅 해서 Files 클래스로도 사용 가능 하다.
			//FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ));
			
			//map()메서드 : map(mode, position, size)인자를 받아 들여 채널의 파일 영역을 메모리에 직접 매핑하는 능력이 있다.
			buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());	
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		if (buffer != null) {
			try {
				Charset charset = Charset.defaultCharset();
				CharsetDecoder decoder = charset.newDecoder();
				CharBuffer charBuffer = decoder.decode(buffer);
				
				String content = charBuffer.toString();
				System.out.println(content);
				
				buffer.clear();
				
			} catch (CharacterCodingException ex) {
				System.err.println(ex);
			}
		}
	}

}
