package java7.nio2.chapter4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileDirectory18 {

	public static void main(String[] args) {
	//BufferedWriter으로 변환
		Path raPath = Paths.get(System.getProperty("user.home"), "Downloads", "racquet.txt");
		
		String string = "\n버퍼 스트림으로 변환 되었습니다.";
		
		try (OutputStream outputStream = Files.newOutputStream(raPath, StandardOpenOption.APPEND);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
			writer.write(string);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
