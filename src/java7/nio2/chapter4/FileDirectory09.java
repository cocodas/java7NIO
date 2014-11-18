package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class FileDirectory09 {

	public static void main(String[] args) {
		//货 颇老 积己窍扁
		Path newFile = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "Test.txt");
		
		//Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw------");
		//FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		
		try {
			Files.createFile(newFile);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}

}
