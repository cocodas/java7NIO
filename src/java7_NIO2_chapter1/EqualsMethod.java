package java7_NIO2_chapter1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EqualsMethod {

	public static void main(String[] args) {
		Path path01 = Paths.get("/Users/Administrator/Downloads/Button.txt");
		Path path02 = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		if (path01.equals(path02)) {
			System.out.println("�� ��δ� ����!!!");
		}else {
			System.out.println("�� ��δ� �ٸ���!!!");
		}
		
		boolean check;
		try {
			check = Files.isSameFile(path01, path02);
			if (check) {
				System.out.println("�� ��δ� ���� �����̴�!");
			}else {
				System.out.println("�� ��δ� ���� ������ �ƴϴ�!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
		//compareTo(); �޼���
		int compare = path01.compareTo(path02);
		System.out.println(compare);
		
		//startsWith() / endsWith() �޼���
		boolean sw = path01.startsWith("/Users/Administrator/Downloads");
		boolean ew = path01.endsWith("Button.txt");
		
		System.out.println(sw);
		System.out.println(ew);
		
		//foreach�� �̿��� ��� �̸� ��� �ݺ��ϱ�
		
		for (Path name : path02) {
			System.out.println(name);
		}
		
		
	}

}
