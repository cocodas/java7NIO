package java7_NIO2_chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ResolveMethod {

	public static void main(String[] args) {
		Path base = Paths.get("C:/Users/Administrator/Downloads");  //고정경로 정의
		
		Path path_1 = base.resolve("Button.txt"); //버튼 파일 추가		
		System.out.println(path_1.toString());
		
		Path path_2 = base.resolve("Space.txt"); // 스페이스 파일 추가		
		System.out.println(path_2.toString());
		
		Path base2 = Paths.get("C:/Users/Administrator/Downloads/Button.txt");  //현제 경로
		
		Path path = base2.resolveSibling("Space.txt"); //현제 경로의 파일을 주어진 파일로 대체 한다.
		System.out.println(path.toString());
		
		
	}

}
