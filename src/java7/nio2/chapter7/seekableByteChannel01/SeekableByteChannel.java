package java7.nio2.chapter7.seekableByteChannel01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class SeekableByteChannel {
	
	//SeekableByteChannel�� ���� �б�
	
	public void seekableChannel(Path path) throws IOException, InterruptedException {
		try {
			java.nio.channels.SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ));
			//12����Ʈ�� ���� ũ�� ����
			ByteBuffer buffer = ByteBuffer.allocate(12);
			String encoding = System.getProperty("file.encoding");
			buffer.clear();
			
			while (((java.nio.channels.SeekableByteChannel) seekableByteChannel).read(buffer) > 0 ) {
				
				//flip() �޼��带 ȣ�� �ϸ� limit�� ���� ��ġ�� �����ϰ� position�� 0���� �����Ѵ�.
				buffer.flip();
				
				//�ѱ��� �а� �ʹٸ�....�ٸ� ����� ����???..
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
