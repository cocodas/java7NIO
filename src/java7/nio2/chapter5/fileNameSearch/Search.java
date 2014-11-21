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
			System.out.println("�˻��� ���� : " + searchedFile + " �� " + file.toRealPath().toString() + " �ȿ� �ֽ��ϴ�.");
			//file��θ� toRealPath()�� ���� ��η� ��ȯ.
			
			found = true;
		}
	}

	@Override
	//preVistDirectory() : preVistDirectory(���͸� ����, ���͸� �⺻ �Ӽ�); ���͸��� �ִ� �׸��� �湮�ϱ� ���� ���͸��� ���� ȣ�� �ȴ�
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {

		return FileVisitResult.CONTINUE;
	}

	@Override
	//visitFile() : visitFile(�����ϴ� ����, ������ �⺻ �Ӽ�); ���͸��� �ִ� ���Ͽ� ���� ȣ�� �Ѵ�.
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		
		search((Path)file);
		if (!found) {
			return FileVisitResult.CONTINUE;
			//CONTINUE  -> ���ϴ� ������ �߰ߵɶ� ���� CONTINUE ��ȯ
		}else {
			return FileVisitResult.TERMINATE;
			//TERMINATE -> ������ �߰��� �Ŀ� TERMINATE ��ȯ
		}
	}

	@Override
	//visitFileFailed() : visitFileFailed(�����ϴ� ����, ������ �湮�ϴ� ���� �߻��� ����); ������ � ������ ������ �� ���� �� ȣ�� �ȴ�.
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	//postVisitDirectory() : postVisitDirectory(���͸��� ���� ����, IOException ��ü); ���͸��� �ִ� �׸��� ��� �湮�ϰų� �湮�� ���ڱ� �ߴܵ� �Ŀ� ȣ�� �ȴ�.
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {

		System.out.println("Visited : " + (Path)dir);
		return FileVisitResult.CONTINUE;
	}

}
