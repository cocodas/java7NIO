package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class ACL_view03 {
	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		try {
			//administrator principal을 찾는다.
			UserPrincipal user = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("administrator");
			
			System.out.println(user);
			
			//ACL 뷰를 가져온다
			AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
			
			//AclEntry.Builder 객체를 사용해 새 항목을 생성한다.
			AclEntry  entry = AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(user).setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.APPEND_DATA).build();
			
			System.out.println("새 항목 생성 : " + entry);
			
			//ACL을 읽는다.
			List<AclEntry> acl = view.getAcl();
			
			System.out.println("ACL 읽기 : " + acl);
			
			//새 항목을 삽입한다.
			acl.add(0, entry);
			
			//acl.removeAll(acl);
			
			System.out.println("새 항목 삽입 후 : " + acl);
	
			//set()이나 setAttribute()를 사용해서 ACL을 작성한다.
			view.setAcl(acl);
			Files.setAttribute(path, "acl:acl", acl, NOFOLLOW_LINKS);
					
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
