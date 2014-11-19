package java7.nio2.chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDirectory19 {

	public static void main(String[] args) {
		//newInputStream()
		Path raPath = Paths.get(System.getProperty("user.home"), "Downloads", "racquet.txt");
		
		
		int n;
		byte[] inBuffer = new byte[1024];
		
		try (InputStream inputStream = Files.newInputStream(raPath)){
			while ((n = inputStream.read(inBuffer)) != -1) {
				System.out.println(new String(inBuffer));
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
