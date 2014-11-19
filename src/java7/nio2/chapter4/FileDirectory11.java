package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileDirectory11 {

	public static void main(String[] args) {
		//write()메서드로 라인쓰기
		Path wikiPath = Paths.get(System.getProperty("user.home"), "Downloads", "wiki.txt");
		
		Charset charset = Charset.forName("UTF-8");
		//Charset class : Charset class는 케릭터셋 자체를 나타내며, 이 클래스의 인스턴스가 나타내는 케릭터셋과
		//유니코드 사이의 변환을 처리해 주는 클래스이다. Charset class의 인스턴스는 생성자를 통해서 생성하지 않고 static으로 
		//제공되는 forName()메서드를 사용해서 생성한다. 예를 들어, 유니코드와 아스키코드 사이의 인코딩/디코딩을 하는 Charset 인스턴스는 다음과 같이 생성할 수 있다.
		//ex) Charset charset = Charset.forName("US-ASCII");(지원하는 케릭터셋 : ISO-8859-1, ISO-8859-15, US-ASCIIUTF-16, UTF-16BE, UTF-16LE, UTF-8, windows-1252)
	    //CharsetEncoder : 케릭터를 바이트로 변환해주는 인코더
		//CharsetDecoder : 바이트 데이터로 부터 케릭터를 생성해주는 디코더
		ArrayList<String> lines = new ArrayList<>();
		lines.add("\n");
		lines.add("성규 형님");
		lines.add("저좀");
		lines.add("그만");
		lines.add("갈구세요!!!");
		//System.out.println(lines);
		
		try {
			Files.write(wikiPath, lines, charset, StandardOpenOption.APPEND);
			//wtite(경로, 쓸대상, 변환할 케릭터 셋, OpenOption); 
			//StandardOpenOption의 종류 : APPEND, READ, WRITE, CREADTE, CREATE_NEW, DELETE_ON_CLOSE, TRUNCATE_EXISTING, SPARSE, SYNC, DSYNC
			//파일을 어떻게 열것인지 또는 생성 할것인지 정하는 옵션
			
		} catch (IOException e) {
			System.err.println(e);
		}
	    
	}

}
