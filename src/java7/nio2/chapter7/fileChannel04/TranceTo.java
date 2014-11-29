package java7.nio2.chapter7.fileChannel04;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class TranceTo {
	
	public void transferTo(Path copyFrom, Path copyTo) {
		System.out.println(" FileChannel Ŭ������ transferTo �޼��带 �̿� �Ͽ� ������ �ű� �ڽ��ϴ�.");
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
			
			//transferTo(�ű� ���� ��ġ, �ִ����Ʈ ��, ���ä��)
			fileChannelFrom.transferTo(0L, fileChannelFrom.size(), fileChannelTo);
			//transferFrom �޼��带 ����ϰ� ������ trasferFrom(������ ä��, �ű� ���� ��ġ, �ִ� ����Ʈ ��)
			//fileChannelTo.transferFrom(fileChannelFrom, 0L, (int)fileChannelFrom.size());
			
			System.out.println(copyFrom.getFileName() + " ������ " + copyFrom.getParent() + " �������� " + copyTo.getParent() + " ������ ���簡 �Ϸ� �Ǿ����ϴ�!!" );
			System.out.println("���� ũ�� : " + fileChannelFrom.size()/1024 + " KB");
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}
	

}
