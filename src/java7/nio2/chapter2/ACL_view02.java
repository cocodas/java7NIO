package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.util.List;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class ACL_view02 {

	public static void main(String[] args) {
		List<AclEntry> acllist = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		try {
			acllist = (List<AclEntry>)Files.getAttribute(path, "acl:acl", NOFOLLOW_LINKS);
			
		} catch (IOException e) {
			System.err.println(e);
		}
				
		//System.out.println(acllist);
		
		for (AclEntry aclentry : acllist) {
			System.out.println("+++++++++++++++++++++++++++++++++++++");
			System.out.println("Principal : " + aclentry.principal().getName());
			System.out.println("Type : " + aclentry.type().toString());
			System.out.println("Permission : " + aclentry.permissions().toString());
			System.out.println("Flags : " + aclentry.flags().toString());
		}
	}

}
