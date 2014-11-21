package java7.nio2.chapter5.PDFFileSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

public class SearchMain {

	public static void main(String[] args) throws IOException{
		
		String words = "���� ���� Java������ ������ Java1000���� �����ϰ� �ִµ���. Java�� ������ ���������� �־����� ���ڴٴ� ���ںе��� ��û�� ���� �޾ҽ��ϴ�. �׷��� Java1000���� �Ϻθ� ���������� ���� �߰��� Java�� ���� 2���� �쳻�� �Ǿ����ϴ�.";
		Search walk = new Search(words);
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path root : dirs) {
			Files.walkFileTree(root, option, Integer.MAX_VALUE, walk);
		}
		
		System.out.println("___________________________________");
		for (String pathString : walk.documents) {
			System.out.println(pathString);
		}
		System.out.println("___________________________________");
		
	}

}
