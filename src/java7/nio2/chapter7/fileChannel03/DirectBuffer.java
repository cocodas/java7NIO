package java7.nio2.chapter7.fileChannel03;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class DirectBuffer {
	//FileChannel과 다이렉트 바이트 버퍼로 파일 복사 하기
	int bufferSizeKB = 4;
	int bufferSize = bufferSizeKB*1024;
	
	public void direct(Path copyFrom, Path copyTo) {
		System.out.println(" @@@ FileChannel과 direct buffer을  사용해서 파일 복사를 사작 하겠습니다. @@@");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				System.err.println(e1);
			}
		
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));	
			
			//다이렉트 바이트 버퍼를 할당 한다.
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
			//비다이렉트 바이트 버퍼를 사용할때
			ByteBuffer byteBuffer2 = ByteBuffer.allocate(bufferSize);
			
			//파일의 데이터를 바이트 버퍼로 읽어 들인다.
			int byteCount;
			int charaterCount = 0;
			
			while ((byteCount = fileChannelFrom.read(byteBuffer)) > 0) {
				charaterCount++;
				//버퍼를 flip()해서 limit를 현제 위치로, position을 0으로 설정한다
				byteBuffer.flip();
				
				//바이트 버퍼의 데이터를 파일에 쓴다.
				fileChannelTo.write(byteBuffer);
				
				System.out.println(charaterCount + "번째 byte 쓰기 완료!! ");
				//다음 읽기를 위해 버퍼 정리
				byteBuffer.clear();
			}
			System.out.println(copyFrom.getFileName() + " 파일이 " + copyFrom.getParent() + " 폴더에서 " + copyTo.getParent() + " 폴더로 복사가 완료 되었습니다!!" );
			System.out.println("파일 크기 : " + fileChannelFrom.size()/1024 + " KB");
			
		} catch (IOException e) {
			System.out.println("이미 파일이 디렉터리에 존재 합니다!!!");
			//System.err.println(e);
		}
		
		
	}

}
