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
			
			//�ʱ� ��ġ�� 0�̾�� �Ѵ�.
			seekableByteChannel.position(0);
			
			System.out.print("position(ó��)���� ���� ù��° ���� �б� : " + "[ " + (seekableByteChannel.position()+1) + " ��° ���� ] ---> ");
			
			seekableByteChannel.read(buffer);
			buffer.flip();
			System.out.println(Charset.forName(encoding).decode(buffer));
			//rewind() : ���۸� �ǰ����� ���۰� ��� �ִ� �����͸� �ٽ� ���� �� �ְ� ���۰� �غ� �ȴ�.(limit�� ������ ������ position�� 0�� �ȴ�.)
			buffer.rewind();
			
			//�߰� ��ġ�� �̵�
			seekableByteChannel.position(seekableByteChannel.size()/2);
			
			System.out.print("position(�߰�)���� ���� ù��° ���� �б� : " + "[ " +seekableByteChannel.position() + " ��° ���� ] ---> ");
			
			seekableByteChannel.read(buffer);
			buffer.flip();
			System.out.println(Charset.forName(encoding).decode(buffer));
			buffer.rewind();
			
			//���������� �̵�
			seekableByteChannel.position(seekableByteChannel.size()-1);
			
			System.out.print("position(������)���� ���� ù��° ���� �б� : " + "[ " + seekableByteChannel.position() + " ��° ���� ] ---> ");
			
			seekableByteChannel.read(buffer);
			buffer.flip();
			System.out.println(Charset.forName(encoding).decode(buffer));
			buffer.clear();
	
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	

}
