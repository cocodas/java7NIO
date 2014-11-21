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
			//file경로를 toRealPath()로 실제 경로로 변환.
			
			found = true;
		}
	}

	@Override
	//preVistDirectory() : preVistDirectory(디렉터리 참조, 디렉터리 기본 속성); 디렉터리에 있는 항목을 방문하기 전에 디렉터리에 대해 호출 된다
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {

		return FileVisitResult.CONTINUE;
	}

	@Override
	//visitFile() : visitFile(참조하는 파일, 파일의 기본 속성); 디렉터리에 있는 파일에 대해 호출 한다.
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		
		search((Path)file);
		if (!found) {
			return FileVisitResult.CONTINUE;
			//CONTINUE  -> 원하는 파일이 발견될때 까지 CONTINUE 반환
		}else {
			return FileVisitResult.TERMINATE;
			//TERMINATE -> 파일을 발견한 후에 TERMINATE 반환
		}
	}

	@Override
	//visitFileFailed() : visitFileFailed(참조하는 파일, 파일을 방문하는 동안 발생된 예외); 파일을 어떤 이유로 접근할 수 없을 때 호출 된다.
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	//postVisitDirectory() : postVisitDirectory(디렉터리에 대한 참조, IOException 객체); 디렉터리에 있는 항목을 모두 방문하거나 방문이 갑자기 중단된 후에 호출 된다.
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {

		System.out.println("Visited : " + (Path)dir);
		return FileVisitResult.CONTINUE;
	}

}
