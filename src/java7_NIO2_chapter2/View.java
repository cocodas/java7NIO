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
		
		//FileStore 파일저장소가 특정 뷰를 지원 하는지 테스트
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
