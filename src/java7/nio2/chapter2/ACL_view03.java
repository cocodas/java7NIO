package java7.nio2.chapter2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

public class ACL_view03 {
	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		try {
			//administrator principal을 찾는다.
			UserPrincipal user = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("administrator");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
