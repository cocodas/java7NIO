package java7.nio2.chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory20 {

	public static void main(String[] args) {
		//BufferedReader로 변환(효율이 향상된다!)
		Path raPath = Paths.get(System.getProperty("user.home"), "Downloads", "racquet.txt");
		
		try (InputStream inputStream = Files.newInputStream(raPath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
	}

}
