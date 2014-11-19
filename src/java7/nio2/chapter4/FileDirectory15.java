package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDirectory15 {

	public static void main(String[] args) {
		//newBufferedWrite()
		Path wikiPath = Paths.get(System.getProperty("user.home"), "Downloads", "wiki.txt");
		
		Charset charset = Charset.forName("UTF-8");
		String txt = "\n 버퍼드 라이트!!";
		
		try (BufferedWriter writer = Files.newBufferedWriter(wikiPath, charset, StandardOpenOption.APPEND)){
			
			writer.write(txt);
			
			
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
