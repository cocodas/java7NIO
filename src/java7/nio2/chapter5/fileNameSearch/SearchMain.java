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
		//Iterable 인터페이스는 자바 컬렉션 클래스의 루트 인터페이스중 하나 이다.
		//Iterator는 개체의 요소들을 출력할때 기존의 인덱스 증감인자에 의존하던 방식을 탈피해서 보다 편리하고 쉽게 출력이 가능하도록 만들어준다.
		//Iterator는 자바 컬렉션 내부에서 자유롭게 쓸수 있으며 기존의 Enumeration의 기능을 완전히 대체함과 동시에 remove( ) 메소드를 장착하고 
		//메소드명들도 줄여서 편리하게 쓸수 있도록 개선되었다. 
		//Iterator이 가지고 있는 메서드 : boolean hasNext( ), E next( ), void remove( )
		//LiseIterator이 가지고 있는 메서드 : boolean hasNext( ), E next( ), void remove( ), int nextIndex( ), boolean hasPrevious( ), 
		//E previous( ), void add(E e), int previousIndex( ), void set(E e) (Iterator의 하위 인스 턴스)
		//IteratorListIterator class 예제 참고

		for (Path root : dirs) {
			if (!walk.found) {
				Files.walkFileTree(root, option, Integer.MAX_VALUE, walk);
			}
		}
		
		if (!walk.found) {
			System.out.println( searchFile + " 파일은 찾을수가 없습니다.! ");
		}
	}

}
