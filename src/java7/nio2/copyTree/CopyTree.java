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
			System.err.println(copyFrom + "�κ��� ������ �� �����ϴ�." + "[ " + e + " ]" );
		}
	}
	
	
	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		
		System.out.println("������ ���͸� : " + (Path) dir); //����ȯ ���� �ʾƵ� �� �۵� �ϴµ� ����ȯ �ϴ� ����????
		
		Path newdir = copyTo.resolve(copyFrom.relativize((Path) dir));
		//relativize() : Returns a URI to represent this path.
		
		System.out.println("���� �Ǵ� ���͸� : " + (Path)newdir);
		
		try {
			Files.copy((Path)dir, newdir, REPLACE_EXISTING,COPY_ATTRIBUTES);
			
		} catch (IOException e) {
			System.out.println(newdir + "�� ����� �����ϴ�." + "[" + e + "]");
			return FileVisitResult.SKIP_SUBTREE;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		System.out.println("���� �� ���� : " + (Path)file);
		copySubTree((Path)file, copyTo.resolve(copyFrom.relativize((Path)file)));
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		if (exc instanceof FileSystemLoopException) {
			System.err.println("Cycle was detected : " + (Path)file);
		}else {
			System.err.println("���� �߻� !! ���� �Ҽ� �����ϴ�!!" + (Path)file +  "[ " + exc + " ]" );
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
				System.err.println("���� ������ ���°� �ƴմϴ� : " + newdir + "[" + e + "]");
			}
			}else {
				throw exc;
			}
		return FileVisitResult.CONTINUE;		
	}

}
