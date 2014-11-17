package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserDefinedFileAttributeView01 {

	public static void main(String[] args) {
		//����� ���� �Ӽ� ���� ���� �˻�
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		try {
			FileStore store = Files.getFileStore(path);
			//FileStore class : name(), supportsFileAttributeView(), type(), isReadOnly(), getTotalSpace(), getUnallocatedSpace()  
			
			if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.class)) {
				//UserDefinedFileAttributeView interface : name(),list(), size(), read(), write(), delete()
				System.out.println("����� ���� �Ӽ��� ���� ���� �ʽ��ϴ�. : " + store);
			}else {
				System.out.println("����� ���� �Ӽ��� ���� �մϴ� : " + store);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}

}
