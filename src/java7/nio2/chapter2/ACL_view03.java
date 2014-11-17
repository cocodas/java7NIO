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
			//administrator principal�� ã�´�.
			UserPrincipal user = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("administrator");
			
			System.out.println(user);
			
			//ACL �並 �����´�
			AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
			
			//AclEntry.Builder ��ü�� ����� �� �׸��� �����Ѵ�.
			AclEntry  entry = AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(user).setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.APPEND_DATA).build();
			
			System.out.println("�� �׸� ���� : " + entry);
			
			//ACL�� �д´�.
			List<AclEntry> acl = view.getAcl();
			
			System.out.println("ACL �б� : " + acl);
			
			//�� �׸��� �����Ѵ�.
			acl.add(0, entry);
			
			//acl.removeAll(acl);
			
			System.out.println("�� �׸� ���� �� : " + acl);
	
			//set()�̳� setAttribute()�� ����ؼ� ACL�� �ۼ��Ѵ�.
			view.setAcl(acl);
			Files.setAttribute(path, "acl:acl", acl, NOFOLLOW_LINKS);
					
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
