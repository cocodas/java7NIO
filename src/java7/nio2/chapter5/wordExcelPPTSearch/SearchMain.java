package java7.nio2.chapter5.wordExcelPPTSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

public class SearchMain {

	public static void main(String[] args) throws IOException{
		
		String words = "Abcde, fghi,jq";
		Search walk = new Search(words);
		@SuppressWarnings("rawtypes")
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
