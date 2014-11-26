package java7.nio2.chapter5.textSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

public class SearchMain {

	public static void main(String[] args) throws IOException{
		
		String words = "background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, .9)))";
		Search walk = new Search(words);
		@SuppressWarnings("rawtypes")
		EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		//EnumSet :  EnumSet�� Set �������̽��� ������� �ϸ鼭 enum ���ſ�ҵ��� �̿��ؼ� ���� ������ �����ϰ� ����� ���� ������ Ÿ������ �����س��� ��ҵ��� ���� ���� ������ 
		//			 �迭ó�� ��ҵ��� �ٷ�� �ִ� ����� �����Ѵ�. EnumTest.java ����
		
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		//getRootDirectories() : ���� �ý��� ��Ʈ ���� 
		
		for (Path root : dirs) {
			Files.walkFileTree(root, opts, Integer.MAX_VALUE, walk);
			//���Ͻý��� ���ȣ���� Ž�� walkFileTree(); 
			//java.nio.file.Files.walkFileTree �޼��带 ���Ͽ� �־��� root directory��� �Ʒ��� ��� ���� �� ������ �ϳ� �ϳ� �湮�ϸ鼭 �־��� ResourceMatcher ��ü�� 
			//��ȯ ���ִ� isMatch����� ���� ������ �߰��ϴ� ����.
			
		}
		
		System.out.println("___________________________________");
		for (String pathString : walk.documents) {
			System.out.println(pathString);
		}
		System.out.println("___________________________________");
		
	}

}
