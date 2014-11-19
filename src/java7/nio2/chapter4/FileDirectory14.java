package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDirectory14 {

	public static void main(String[] args) {
		//readAllLines() 메서드로 읽기
		Path wikiPath = Paths.get(System.getProperty("user.home"), "Downloads", "wiki.txt");
		
		Charset charset = Charset.forName("UTF-8");
		
		try {
			List<String> lines = Files.readAllLines(wikiPath, charset);
			//System.out.println(lines);
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
