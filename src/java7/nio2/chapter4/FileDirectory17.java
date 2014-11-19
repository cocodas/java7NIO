package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDirectory17 {

	public static void main(String[] args) {
		//newOutputStream()
		Path raPath = Paths.get(System.getProperty("user.home"), "Downloads", "racquet.txt");
		String racquet = "racquet의 뜻 : 라켓을 집어 던지는 행위";
		
		byte data[] = racquet.getBytes();
		
		try {
			OutputStream outputStream = Files.newOutputStream(raPath);
			outputStream.write(data);
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
