package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDirectory10 {

	public static void main(String[] args) {
		//write() 메서드로 바이트 쓰기
		Path ballPath = Paths.get(System.getProperty("user.home"), "Downloads", "ball.png");
		
		byte[] ballBytes = new byte[]{
				(byte)0x89,(byte)0x50,(byte)0x4e,(byte)0x47,(byte)0x0d,(byte)0x0a,(byte)0x1a,(byte)0x0a,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0d,(byte)0x49,(byte)0x48,(byte)0x44,(byte)0x52,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x10,
				(byte)0x08,(byte)0x02,(byte)0x00,

				(byte)0x49,(byte)0x45,(byte)0x4e,(byte)0x44,(byte)0xae,(byte)0x42,(byte)0x60,(byte)0x82
		};
		
		try {
			Files.write(ballPath, ballBytes);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//텍스트를 write()메서드를 사용하여 작성하기
		Path wikiPath = Paths.get(System.getProperty("user.home"),"Downloads", "wiki.txt");
		
		String wiki = "Rafeal \"Rafa\" Nadal Parera (born 3 June 1986) is a Spanish professional tennis" + "\nplayer and a former World No.1 AS of 29 Agust 2011 (2011 -08-29)[update],"
				+ "\nhe is ranked No.2" + "by the Association of Tennis Professionas(ATP) \"The King of Clay\", \nand~~~~" + "~~~~ Some of his best wins are : "
						+ "썬큐형님 그만 갈구세요!!~ㅎ";
		
		try {
			byte[] wikiByte = wiki.getBytes("UTF-8");
			Files.write(wikiPath, wikiByte);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
		
		
	}

}
