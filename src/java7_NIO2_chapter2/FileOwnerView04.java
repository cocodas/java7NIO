package java7_NIO2_chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;

public class FileOwnerView04 {

	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		FileOwnerAttributeView foav = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		
		try {
			String owner = foav.getOwner().getName();
			System.out.println(owner);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
