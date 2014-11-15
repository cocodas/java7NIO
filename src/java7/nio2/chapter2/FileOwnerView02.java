package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

public class FileOwnerView02 {

	public static void main(String[] args) {
		UserPrincipal owner = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		FileOwnerAttributeView foav = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		
		try {
			owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("administrator");
			foav.setOwner(owner);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		System.out.println(owner);
	}

}
