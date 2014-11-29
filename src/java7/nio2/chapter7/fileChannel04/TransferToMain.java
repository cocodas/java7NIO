package java7.nio2.chapter7.fileChannel04;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TransferToMain {

	public static void main(String[] args) {

		Path copyFrom = Paths.get(System.getProperty("user.home"), "Desktop", "���� ����ŷ", "��Ŀ�� ���� ����ŷ", "DAY20_ȸ��3.mp3");
		Path copyTo = Paths.get(System.getProperty("user.home"), "Speaking Test", "DAY20_ȸ��3.mp3");
		
		TranceTo fileCopy = new TranceTo();
		
		fileCopy.transferTo(copyFrom, copyTo);
	}

}
