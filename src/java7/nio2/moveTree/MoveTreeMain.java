package java7.nio2.moveTree;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class MoveTreeMain {

	public static void main(String[] args) throws IOException {

		Path moveFrom = Paths.get(System.getProperty("user.home"), "rafaelnadal");
		Path moveTo = Paths.get(System.getProperty("user.home"), "rafaelnadal_Move");
		
		MoveTree walk = new MoveTree(moveFrom, moveTo);
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		Files.walkFileTree(moveFrom, option, Integer.MAX_VALUE, walk);
	}

}
