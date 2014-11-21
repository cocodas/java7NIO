package java7.nio2.chapter5.GlobPatternSearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;

public class Search implements FileVisitor {
	private final PathMatcher matcher;
	
	public Search(String glob) {
		matcher = FileSystems.getDefault().getPathMatcher("glob : " + glob);
	}
	
	void search(Path file) throws IOException{
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			System.out.println("검색한 파일 : " + name + "검색된 경로 : " + file.toRealPath().toString());
		}
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		search((Path)file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		//필요 하다면 에러를 보고 하는곳
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		System.out.println("Visited : " + (Path)dir);
		return FileVisitResult.CONTINUE;
	}

}
