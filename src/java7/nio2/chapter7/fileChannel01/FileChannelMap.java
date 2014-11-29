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
			//FileChannel�� ĳ���� �ؼ� Files Ŭ�����ε� ��� ���� �ϴ�.
			//FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ));
			
			//map()�޼��� : map(mode, position, size)���ڸ� �޾� �鿩 ä���� ���� ������ �޸𸮿� ���� �����ϴ� �ɷ��� �ִ�.
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
