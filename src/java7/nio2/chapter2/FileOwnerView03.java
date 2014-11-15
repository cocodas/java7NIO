package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileOwnerView03 {

	public static void main(String[] args) {
		UserPrincipal owner = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		try {
			owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("administrator");
			Files.setAttribute(path, "owner:owner", owner, NOFOLLOW_LINKS);
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
