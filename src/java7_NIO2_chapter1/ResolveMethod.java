package java7_NIO2_chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ResolveMethod {

	public static void main(String[] args) {
		Path base = Paths.get("C:/Users/Administrator/Downloads");  //������� ����
		
		Path path_1 = base.resolve("Button.txt"); //��ư ���� �߰�		
		System.out.println(path_1.toString());
		
		Path path_2 = base.resolve("Space.txt"); // �����̽� ���� �߰�		
		System.out.println(path_2.toString());
		
		Path base2 = Paths.get("C:/Users/Administrator/Downloads/Button.txt");  //���� ���
		
		Path path = base2.resolveSibling("Space.txt"); //���� ����� ������ �־��� ���Ϸ� ��ü �Ѵ�.
		System.out.println(path.toString());
		
		
	}

}
