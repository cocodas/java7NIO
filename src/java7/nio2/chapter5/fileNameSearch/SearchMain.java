package java7.nio2.chapter5.fileNameSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class SearchMain {

	public static void main(String[] args) throws IOException {
		
		Path searchFile = Paths.get("suji.jpg");
		Search walk = new Search(searchFile);
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		//Iterable �������̽��� �ڹ� �÷��� Ŭ������ ��Ʈ �������̽��� �ϳ� �̴�.
		//Iterator�� ��ü�� ��ҵ��� ����Ҷ� ������ �ε��� �������ڿ� �����ϴ� ����� Ż���ؼ� ���� ���ϰ� ���� ����� �����ϵ��� ������ش�.
		//Iterator�� �ڹ� �÷��� ���ο��� �����Ӱ� ���� ������ ������ Enumeration�� ����� ������ ��ü�԰� ���ÿ� remove( ) �޼ҵ带 �����ϰ� 
		//�޼ҵ��鵵 �ٿ��� ���ϰ� ���� �ֵ��� �����Ǿ���. 
		//Iterator�� ������ �ִ� �޼��� : boolean hasNext( ), E next( ), void remove( )
		//LiseIterator�� ������ �ִ� �޼��� : boolean hasNext( ), E next( ), void remove( ), int nextIndex( ), boolean hasPrevious( ), 
		//E previous( ), void add(E e), int previousIndex( ), void set(E e) (Iterator�� ���� �ν� �Ͻ�)
		//IteratorListIterator class ���� ����

		for (Path root : dirs) {
			if (!walk.found) {
				Files.walkFileTree(root, option, Integer.MAX_VALUE, walk);
			}
		}
		
		if (!walk.found) {
			System.out.println( searchFile + " ������ ã������ �����ϴ�.! ");
		}
	}

}
