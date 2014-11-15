package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.util.List;


public class ACL_view01 {

	public static void main(String[] args) {
		List<AclEntry> acllist = null;
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		AclFileAttributeView aclview = Files.getFileAttributeView(path, AclFileAttributeView.class);
		try {
			acllist = aclview.getAcl();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
