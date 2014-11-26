package java7.nio2.chapter5.delectDirectory;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class DeleteDirectoryMain {

	public static void main(String[] args) throws IOException {
		
		Path directory = Paths.get(System.getProperty("user.home"), "rafaelnadal");
//		System.out.println(directory.toString());
		DelectDyrectory walk = new DelectDyrectory();
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		Files.walkFileTree(directory, option, Integer.MAX_VALUE, walk);
	}

}
