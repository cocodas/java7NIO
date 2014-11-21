package java7.nio2.chapter5.PDFFileSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

public class SearchMain {

	public static void main(String[] args) throws IOException{
		
		String words = "요즘 제가 Java예제를 정리한 Java1000제를 집필하고 있는데요. Java의 정석에 연습문제가 있었으면 좋겠다는 독자분들의 요청을 많이 받았습니다. 그래서 Java1000제의 일부를 연습문제로 만들어서 추가한 Java의 정석 2판을 펴내게 되었습니다.";
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
