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
		//EnumSet :  EnumSet은 Set 인터페이스를 기반으로 하면서 enum 열거요소들을 이용해서 보다 빠르고 강력하게 결과를 도출 열거형 타입으로 지정해놓은 요소들을 가장 쉽고 빠르게 
		//			 배열처럼 요소들을 다룰수 있는 기능을 제공한다. EnumTest.java 참고
		
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		//getRootDirectories() : 파일 시스템 루트 나열 
		
		for (Path root : dirs) {
			Files.walkFileTree(root, opts, Integer.MAX_VALUE, walk);
			//파일시스템 재귀호출형 탐색 walkFileTree(); 
			//java.nio.file.Files.walkFileTree 메서드를 통하여 주어진 root directory경로 아래의 모든 폴더 및 파일을 하나 하나 방문하면서 주어진 ResourceMatcher 객체가 
			//반환 해주는 isMatch결과에 따라서 파일을 추가하는 구조.
			
		}
		
		System.out.println("___________________________________");
		for (String pathString : walk.documents) {
			System.out.println(pathString);
		}
		System.out.println("___________________________________");
		
	}

}
