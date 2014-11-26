package java7.nio2.chapter5.copyTree;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class CopyTreeMain {

	public static void main(String[] args) throws IOException {
		Path copyFrom = Paths.get(System.getProperty("user.home"), "rafaelnadal");
		Path copyTo = Paths.get(System.getProperty("user.home"), "rafaelnada_copy");
		
		CopyTree walk = new CopyTree(copyFrom, copyTo);
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		Files.walkFileTree(copyFrom, option, Integer.MAX_VALUE, walk);
	}

}
