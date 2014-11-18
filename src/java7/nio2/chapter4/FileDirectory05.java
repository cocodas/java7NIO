package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FileDirectory05 {

	public static void main(String[] args) {
		//�� ���丮 �����
		Path newdir = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "Button.txt");
		
		//�Ӽ� ��Ʈ �߰�
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
		//PosixFilePermissions.fromString() : �ڵ忡 ���� �� ���ڿ�(��rw-r--r--��)�� ������ ������ ������ �� �ִ�.
		
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		//PosixFilePermissions.asFileAttribute() : ���� ���� Set�� �޾Ƽ� FileAttribute�� �����Ѵ�. �׸��� Path.createFile()�̳� Path.createDirectory()�� �Ӽ��� �����Ѵ�.
		
		try {
			Files.createDirectories(newdir, attr);
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
