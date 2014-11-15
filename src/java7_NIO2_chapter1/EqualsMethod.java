package java7_NIO2_chapter1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EqualsMethod {

	public static void main(String[] args) {
		Path path01 = Paths.get("/Users/Administrator/Downloads/Button.txt");
		Path path02 = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		if (path01.equals(path02)) {
			System.out.println("두 경로는 같다!!!");
		}else {
			System.out.println("두 경로는 다르다!!!");
		}
		
		boolean check;
		try {
			check = Files.isSameFile(path01, path02);
			if (check) {
				System.out.println("두 경로는 같은 파일이다!");
			}else {
				System.out.println("두 경로는 같은 파일이 아니다!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
		//compareTo(); 메서드
		int compare = path01.compareTo(path02);
		System.out.println(compare);
		
		//startsWith() / endsWith() 메서드
		boolean sw = path01.startsWith("/Users/Administrator/Downloads");
		boolean ew = path01.endsWith("Button.txt");
		
		System.out.println(sw);
		System.out.println(ew);
		
		//foreach를 이용한 경로 이름 요소 반복하기
		
		for (Path name : path02) {
			System.out.println(name);
		}
		
		
	}

}
