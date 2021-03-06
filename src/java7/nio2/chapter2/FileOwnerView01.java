package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

public class FileOwnerView01 {

	public static void main(String[] args) {
		//파일 소유자 설정
		UserPrincipal owner = null;
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		try {
			owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("administrator");
			Path pathOwner = Files.setOwner(path, owner);
			System.out.println("pathOwner: " + pathOwner.toString());
		} catch (IOException e) {
			System.err.print(e);
		}
		System.out.println("파일 소유자: " + owner.getName());
	}

}
