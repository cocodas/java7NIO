package java7.nio2.chapter7.seekableByteChannel03;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Seekable {
	
	ByteBuffer buffer = ByteBuffer.allocate(1);
	String encoding = System.getProperty("file.encoding");
	
	public void seekableByteChannel(Path path) {
		
		try {
			SeekableByteChannel seekableByteChannel = (Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ)));
			
			//초기 위치는 0이어야 한다.
			seekableByteChannel.position(0);
			
			System.out.print("position(처음)으로 부터 첫번째 문자 읽기 : " + "[ " + (seekableByteChannel.position()+1) + " 번째 문자 ] ---> ");
			
			seekableByteChannel.read(buffer);
			buffer.flip();
			System.out.println(Charset.forName(encoding).decode(buffer));
			//rewind() : 버퍼를 되감으면 버퍼가 담고 있는 데이터를 다시 읽을 수 있게 버퍼가 준비 된다.(limit는 변하지 않지만 position은 0이 된다.)
			buffer.rewind();
			
			//중간 위치로 이동
			seekableByteChannel.position(seekableByteChannel.size()/2);
			
			System.out.print("position(중간)으로 부터 첫번째 문자 읽기 : " + "[ " +seekableByteChannel.position() + " 번째 문자 ] ---> ");
			
			seekableByteChannel.read(buffer);
			buffer.flip();
			System.out.println(Charset.forName(encoding).decode(buffer));
			buffer.rewind();
			
			//마지막으로 이동
			seekableByteChannel.position(seekableByteChannel.size()-1);
			
			System.out.print("position(마지막)으로 부터 첫번째 문자 읽기 : " + "[ " + seekableByteChannel.position() + " 번째 문자 ] ---> ");
			
			seekableByteChannel.read(buffer);
			buffer.flip();
			System.out.println(Charset.forName(encoding).decode(buffer));
			buffer.clear();
	
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	

}
