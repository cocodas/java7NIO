package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileOwnerView05 {

	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		try {
			UserPrincipal owner = (UserPrincipal)Files.getAttribute(path, "owner:owner", NOFOLLOW_LINKS);
			
			System.out.println(owner.getName());
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
