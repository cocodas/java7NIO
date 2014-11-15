package java7_NIO2_chapter2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.Set;

public class View {

	public static void main(String[] args) {
		FileSystem fs = FileSystems.getDefault();
		Set<String> views = fs.supportedFileAttributeViews();
		
		for (String view : views) {
			System.out.println(view);
		}
		
		//FileStore ��������Ұ� Ư�� �並 ���� �ϴ��� �׽�Ʈ
		for (FileStore store : fs.getFileStores()) {
			boolean supported = store.supportsFileAttributeView(BasicFileAttributeView.class);
			System.out.println(store.name() + "---" + supported);
		}
		
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		FileStore store;
		try {
			store = Files.getFileStore(path);
			boolean supported = store.supportsFileAttributeView("basic");
			System.out.println(store.name() + "---" + supported);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
	}

}
