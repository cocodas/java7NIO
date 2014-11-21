package java7.nio2.chapter5.GlobPatternSearch;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class SearchMain {

	public static void main(String[] args) throws IOException {
		
		String glob = "*.jpg";
		Path fileTree = Paths.get(System.getProperty("user.home"));
		Search walk = new Search(glob);
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		Files.walkFileTree(fileTree, option, Integer.MAX_VALUE, walk);
	}

}
