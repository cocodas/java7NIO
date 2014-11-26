package java7.nio2.chapter5.moveTree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

public class MoveTree implements FileVisitor {

	private final Path moveFrom;
	private final Path moveTo;
	static FileTime time = null;
	
	public MoveTree(Path moveFrom, Path moveTo) {
		this.moveFrom = moveFrom;
		this.moveTo = moveTo;
	}
	
	
	static void moveSubTree(Path moveFrom, Path moveTo) throws IOException{
		try {
			Files.move(moveFrom, moveTo, REPLACE_EXISTING, ATOMIC_MOVE);
		} catch (IOException e) {
			System.err.println(moveFrom + "을 옮길수 없습니다." + "[ " + e + " ]");
		}
		
	}
	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		Path newdir = moveTo.resolve(moveFrom.relativize((Path) dir));
		try {
			Files.setLastModifiedTime(newdir, time);
			Files.delete((Path)dir);
			
		} catch (IOException e) {
			System.err.println("모든 디렉터리 속성을 " + newdir + "에 복사할 수 없습니다." + "[ " + e + " ]" );
		}
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		
		System.out.println("이동할 디렉터리 : " + (Path)dir);
		Path newdir = moveTo.resolve(moveFrom.relativize((Path) dir));
		
		try {
			Files.copy((Path)dir, newdir, REPLACE_EXISTING, COPY_ATTRIBUTES);
			time = Files.getLastModifiedTime((Path)dir);
		} catch (IOException e) {
			System.err.println(newdir + "에 옮길 수 없습니다. " + "[ " + e + " ]");
			return FileVisitResult.SKIP_SUBTREE;
		}
		
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		
		System.out.println("옮길 파일 : " + (Path) file);
		System.out.println("------------------------------");
		
		moveSubTree((Path)file, moveTo.resolve(moveFrom.relativize((Path) file)));
		
		return FileVisitResult.CONTINUE;

	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}


}
