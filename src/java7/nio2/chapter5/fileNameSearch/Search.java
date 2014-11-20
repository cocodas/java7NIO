package java7.nio2.chapter5.fileNameSearch;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Search implements FileVisitor{

	private final Path searchedFile;
	public boolean found;
	
	public Search(Path searchedFile) {
		this.searchedFile = searchedFile;
		this.found = false;
	}
	
	void search(Path file) throws IOException {
		Path name = file.getFileName();
		if (name != null && name.equals(searchedFile)) {
			System.out.println("#####################################################################");
			System.out.println("검색된 파일 : " + searchedFile + " 은 " + file.toRealPath().toString() + " 안에 있습니다.");
			
			found = true;
		}
	}

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		
		search((Path)file);
		if (!found) {
			return FileVisitResult.CONTINUE;
		}else {
			return FileVisitResult.TERMINATE;
		}
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {

		System.out.println("Visited : " + (Path)dir);
		return FileVisitResult.CONTINUE;
	}

}
