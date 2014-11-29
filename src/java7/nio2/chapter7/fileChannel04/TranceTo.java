package java7.nio2.chapter7.fileChannel04;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class TranceTo {
	
	public void transferTo(Path copyFrom, Path copyTo) {
		System.out.println(" FileChannel 클래스의 transferTo 메서드를 이용 하여 파일을 옮기 겠습니다.");
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			//transferTo(옮길 시작 위치, 최대바이트 수, 대상채널)
			fileChannelFrom.transferTo(0L, fileChannelFrom.size(), fileChannelTo);
			//transferFrom 메서드를 사용하고 싶으면 trasferFrom(복사할 채널, 옮길 시작 위치, 최대 바이트 수)
			//fileChannelTo.transferFrom(fileChannelFrom, 0L, (int)fileChannelFrom.size());
			
			System.out.println(copyFrom.getFileName() + " 파일이 " + copyFrom.getParent() + " 폴더에서 " + copyTo.getParent() + " 폴더로 복사가 완료 되었습니다!!" );
			System.out.println("파일 크기 : " + fileChannelFrom.size()/1024 + " KB");
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}
	

}
