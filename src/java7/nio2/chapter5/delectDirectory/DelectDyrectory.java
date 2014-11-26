package java7.nio2.chapter5.delectDirectory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.poi.hssf.record.EscherAggregate;

public class DelectDyrectory implements FileVisitor {
	
	boolean deleteFileByFile(Path file) throws IOException{
		return Files.deleteIfExists(file);
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		
		if (exc == null) {
			System.out.println("Visited : " + (Path) dir);
			boolean success = deleteFileByFile((Path)dir);
			
			if (success) {
				System.out.println("삭제된 디렉터리 : " + (Path)dir);
			}else {
				System.out.println("삭제되지 않은 디렉터리 : " + (Path)dir);
			}
		}else {
			throw exc;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		
		boolean success = deleteFileByFile((Path) file);
		if (success) {
			System.out.println("삭제된 파일 : " + (Path) file);
		}else {
			System.out.println("삭제되지 않은 파일 : " + (Path) file);
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {

		return FileVisitResult.CONTINUE;
	}


}
