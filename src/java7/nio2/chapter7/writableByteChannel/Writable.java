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
		//WritableByteChannel�� ����ؼ� ���Ͽ� ����.
		try {
			//���⼭�� �ɼ��� ������ ��� �Ѵ�. APPED -> �� ���� �ٿ� ����.
			WritableByteChannel writableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.WRITE, StandardOpenOption.APPEND));
			ByteBuffer buffer = ByteBuffer.wrap("\n rolendSunq Bro is babo".getBytes());
			
			int write = writableByteChannel.write(buffer);
			
			System.out.println("�� ���µ� ���� ����Ʈ �� : " + write + " Byte");
			
			buffer.clear();
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
