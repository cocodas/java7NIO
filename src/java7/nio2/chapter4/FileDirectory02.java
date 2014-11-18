package java7.nio2.chapter4;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileDirectory02 {

	public static void main(String[] args) {
		//���� ���ټ� �˻� 
		Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "Button.txt");
		
		boolean isReadable = Files.isReadable(path);
		boolean isWritable = Files.isWritable(path);
		boolean isExecutable = Files.isExecutable(path);
		boolean isRegular = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
		
		//System.out.println(isReadable );
		
		if (isReadable && isWritable && isExecutable && isRegular) {
			System.out.println("���� �˻� ����!!!");
		}else {
			System.out.println("������ �˻� �Ҽ� �����ϴ�!!");
		}
		
		//�� �ڵ������� �ٿ���
		
		boolean isAccessible = Files.isReadable(path) & Files.isWritable(path) & Files.isExecutable(path) & Files.isReadable(path);
		
		if (isAccessible) {
			System.out.println("���� �˻� ���� !!");
		}else {
			System.out.println("������ �˻� �� �� �����ϴ�.!!");
		}
	}

}
