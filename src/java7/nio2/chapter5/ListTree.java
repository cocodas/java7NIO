package java7.nio2.chapter5;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public class ListTree extends SimpleFileVisitor<Path> {
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc){
		
		System.out.println("방문한 디렉터리 : " + dir.toString());
		
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		
		System.out.println(exc);
		
		return FileVisitResult.CONTINUE;
		
	}
}
