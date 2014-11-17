package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserDefinedFileAttributeView01 {

	public static void main(String[] args) {
		//사용자 정의 속성 지원 여부 검사
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "dojo.pdf");
		
		try {
			FileStore store = Files.getFileStore(path);
			//FileStore class : name(), supportsFileAttributeView(), type(), isReadOnly(), getTotalSpace(), getUnallocatedSpace()  
			
			if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.class)) {
				//UserDefinedFileAttributeView interface : name(),list(), size(), read(), write(), delete()
				System.out.println("사용자 정의 속성을 지원 하지 않습니다. : " + store);
			}else {
				System.out.println("사용자 정의 속성을 지원 합니다 : " + store);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}

}
