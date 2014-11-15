package java7.nio2.chapter1;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest02 {

	public static void main(String[] args) {
		Path path = Paths.get("/Users/Administrator/Downloads", "Button.txt");
		
		//���ڿ��� ��ȯ
		String path_to_string = path.toString();
		System.out.println("���� ���� ��ȯ : " + path_to_string);
		
		//��θ� URI�� ��ȯ
		URI path_to_uri = path.toUri();
		System.out.println("��θ� URI�� ��ȯ : " + path_to_uri);
		
		//��� ��� -> ���� ���
		Path path_to_absolue_path = path.toAbsolutePath();
		System.out.println("��� ��� -> ���� ��� : " + path_to_absolue_path);
		
		//��θ� ���� ��η� ��ȯ toRealPath()�޼���� ������ ���ų� ���� �Ҽ� ���ٸ� IOException ���ܸ� ������. 
		try {
			Path real_path = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
			System.out.println("���� ��η� ��ȯ : " + real_path);
		} catch (NoSuchFileException e) {
			System.out.println(e);
		}catch (IOException e) {
			System.out.println(e);
		}
		
		//��θ� ���Ϸ� ��ȯ �ϱ� toFile() : Path��ü -> File��ü  toPath() : File��ü -> Path��ü
		
		File path_to_file = path.toFile();
		Path file_to_path = path_to_file.toPath();
		
		System.out.println("Path to file name : " + path_to_file.getName());
		System.out.println("File to path : " + file_to_path.toString());
	}

}
