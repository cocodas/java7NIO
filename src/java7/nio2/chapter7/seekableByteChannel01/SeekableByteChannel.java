package java7.nio2.chapter7.seekableByteChannel01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class SeekableByteChannel {
	
	//SeekableByteChannel로 파일 읽기
	
	public void seekableChannel(Path path) throws IOException, InterruptedException {
		try {
			java.nio.channels.SeekableByteChannel seekableByteChannel = Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ));
			//12바이트로 버퍼 크기 설정
			ByteBuffer buffer = ByteBuffer.allocate(12);
			String encoding = System.getProperty("file.encoding");
			buffer.clear();
			
			while (((java.nio.channels.SeekableByteChannel) seekableByteChannel).read(buffer) > 0 ) {
				
				//flip() 메서드를 호출 하면 limit을 현제 위치로 설정하고 position을 0으로 설정한다.
				buffer.flip();
				
				//한글을 읽고 싶다면....다른 방법은 없나???..
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
