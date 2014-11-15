package java7.nio2.chapter1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest01 {

	public static void main(String[] args) {
		Path path = Paths.get("C:/Users/Administrator/Downloads/Button.txt");
		
		System.out.println("파일/ 디렉터리이름 : " + path.getFileName());
		System.out.println("경로 루트 : " + path.getRoot());
		System.out.println("경로의 부모 : " + path.getParent());
		System.out.println("경로 요수 갯수 : " + path.getNameCount() + "개");

		//String[] name = new String[path.getNameCount()];
		
		for (int i = 0; i < path.getNameCount(); i++) {
		//Path name = path.getName(i);
		//System.out.print(name);
			System.out.println(i+1 + "번째 요소 이름은 : " + path.getName(i));
		}
		
		System.out.println("3번째 까지 상대 경로 추출 : " + path.subpath(0, 3));
		
	}

}
