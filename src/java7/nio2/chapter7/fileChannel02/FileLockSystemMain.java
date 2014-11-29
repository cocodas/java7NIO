package java7.nio2.chapter7.fileChannel02;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLockSystemMain {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "email", "cocodas.txt");
		
		FileLockSystem lockSystem = new FileLockSystem();
		
		lockSystem.fileLock(path);
	}

}
