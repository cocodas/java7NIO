package java7.nio2.chapter5;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class RecursiveWalk01 {

	public static void main(String[] args) {
		//��� ó��
		//���� Ʈ�� ���� ��ġ ����
		Path listDir = Paths.get(System.getProperty("user.home"), "Downloads");
		
		//walk �ν��Ͻ� ����
		ListTree walk = new ListTree();
		
		//��ũ FOLLOW
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		
		try {
			//walk ����
			Files.walkFileTree(listDir, option, Integer.MAX_VALUE, walk);
			//walkFileTree(������ġ, walk�� ������ �ɼ�, �湮�� �ִ� ���͸� ������ �� (��� ���͸� ��ȸ : Intrger.MAX_VALUE), walk �ν��Ͻ�);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
