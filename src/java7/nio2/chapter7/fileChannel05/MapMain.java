package java7.nio2.chapter7.fileChannel05;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MapMain {

	public static void main(String[] args) {
		Path copyFrom = Paths.get(System.getProperty("user.home"), "Desktop", "���� ����ŷ", "��Ŀ�� ���� ����ŷ", "DAY20_ȸ��3.mp3");
		Path copyTo = Paths.get(System.getProperty("user.home"), "Speaking Test", "DAY20_ȸ��3.mp3");
		
		Map mapCopy = new Map();
		mapCopy.usingMap(copyFrom, copyTo);
		
		
	}

}
