package java7.nio2.copyTree;

import java.io.IOException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING; 
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;; 

public class CopyTree implements FileVisitor {
	private final Path copyFrom;
	private final Path copyTo;
	
	public CopyTree(Path copyFrom, Path copyTo) {
		this.copyFrom = copyFrom;
		this.copyTo = copyTo;
	}
	
	static void copySubTree(Path copyFrom, Path copyTo) throws IOException{
		try {
			Files.copy(copyFrom, copyTo, REPLACE_EXISTING, COPY_ATTRIBUTES);
			
		} catch (IOException e) {
			System.err.println(copyFrom + "로부터 복사할 수 없습니다." + "[ " + e + " ]" );
		}
	}
	
	
	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		
		System.out.println("복사할 디렉터리 : " + (Path) dir); //형변환 하지 않아도 잘 작동 하는데 형변환 하는 이유????
		
		Path newdir = copyTo.resolve(copyFrom.relativize((Path) dir));
		//relativize() : Returns a URI to represent this path.
		
		System.out.println("복사 되는 디렉터리 : " + (Path)newdir);
		
		try {
			Files.copy((Path)dir, newdir, REPLACE_EXISTING,COPY_ATTRIBUTES);
			
		} catch (IOException e) {
			System.out.println(newdir + "에 만들수 없습니다." + "[" + e + "]");
			return FileVisitResult.SKIP_SUBTREE;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		System.out.println("복사 할 파일 : " + (Path)file);
		copySubTree((Path)file, copyTo.resolve(copyFrom.relativize((Path)file)));
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		if (exc instanceof FileSystemLoopException) {
			System.err.println("Cycle was detected : " + (Path)file);
		}else {
			System.err.println("에러 발생 !! 복사 할수 없습니다!!" + (Path)file +  "[ " + exc + " ]" );
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		
		if (exc == null) {
			Path newdir = copyTo.resolve(copyFrom.relativize((Path)dir));
			try {
				FileTime time = Files.getLastModifiedTime((Path)dir);
				Files.setLastModifiedTime(newdir, time);
				
			}catch (IOException e) {
				System.err.println("복사 가능한 상태가 아닙니다 : " + newdir + "[" + e + "]");
			}
			}else {
				throw exc;
			}
		return FileVisitResult.CONTINUE;		
	}

}
