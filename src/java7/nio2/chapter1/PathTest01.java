package java7.nio2.chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest01 {

	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		System.out.println("����/ ���͸��̸� : " + path.getFileName());
		System.out.println("��� ��Ʈ : " + path.getRoot());
		System.out.println("����� �θ� : " + path.getParent());
		System.out.println("��� ��� ���� : " + path.getNameCount() + "��");

		//String[] name = new String[path.getNameCount()];
		
		for (int i = 0; i < path.getNameCount(); i++) {
		//Path name = path.getName(i);
		//System.out.print(name);
			System.out.println(i+1 + "��° ��� �̸��� : " + path.getName(i));
		}
		
		System.out.println("3��° ���� ��� ��� ���� : " + path.subpath(0, 3));
		
	}

}
