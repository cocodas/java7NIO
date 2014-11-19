package java7.nio2.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory16 {

	public static void main(String[] args) {
		//newBufferedReader()
		Path wikiPath = Paths.get(System.getProperty("user.home"), "Downloads", "wiki.txt");
		
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(wikiPath, charset)){
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
