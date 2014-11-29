package java7.nio2.chapter7.fileChannel03;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class DirectBuffer {
	//FileChannel�� ���̷�Ʈ ����Ʈ ���۷� ���� ���� �ϱ�
	int bufferSizeKB = 4;
	int bufferSize = bufferSizeKB*1024;
	
	public void direct(Path copyFrom, Path copyTo) {
		System.out.println(" @@@ FileChannel�� direct buffer��  ����ؼ� ���� ���縦 ���� �ϰڽ��ϴ�. @@@");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				System.err.println(e1);
			}
		
		try {
			FileChannel fileChannelFrom = FileChannel.open(copyFrom, EnumSet.of(StandardOpenOption.READ));
			FileChannel fileChannelTo = FileChannel.open(copyTo, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));	
			
			//���̷�Ʈ ����Ʈ ���۸� �Ҵ� �Ѵ�.
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
			//����̷�Ʈ ����Ʈ ���۸� ����Ҷ�
			ByteBuffer byteBuffer2 = ByteBuffer.allocate(bufferSize);
			
			//������ �����͸� ����Ʈ ���۷� �о� ���δ�.
			int byteCount;
			int charaterCount = 0;
			
			while ((byteCount = fileChannelFrom.read(byteBuffer)) > 0) {
				charaterCount++;
				//���۸� flip()�ؼ� limit�� ���� ��ġ��, position�� 0���� �����Ѵ�
				byteBuffer.flip();
				
				//����Ʈ ������ �����͸� ���Ͽ� ����.
				fileChannelTo.write(byteBuffer);
				
				System.out.println(charaterCount + "��° byte ���� �Ϸ�!! ");
				//���� �б⸦ ���� ���� ����
				byteBuffer.clear();
			}
			System.out.println(copyFrom.getFileName() + " ������ " + copyFrom.getParent() + " �������� " + copyTo.getParent() + " ������ ���簡 �Ϸ� �Ǿ����ϴ�!!" );
			System.out.println("���� ũ�� : " + fileChannelFrom.size()/1024 + " KB");
			
		} catch (IOException e) {
			System.out.println("�̹� ������ ���͸��� ���� �մϴ�!!!");
			//System.err.println(e);
		}
		
		
	}

}
