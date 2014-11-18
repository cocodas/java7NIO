package java7.nio2.chapter4;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileDirectory04 {

	public static void main(String[] args) {
		//���� �ý��� ��Ʈ ���͸� ��� �����ϱ�
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		ArrayList<Path> list = new ArrayList<Path>();
		
		for (Path name : dirs) {
			//System.out.println(name);
			list.add(name);
		}
		
		Path[] arr = new Path[list.size()];
		list.toArray(arr);
		
		for (Path path : arr) {
			System.out.println(path);
		}
		
	}

}
