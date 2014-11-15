package java7.nio2.chapter1;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest02 {

	public static void main(String[] args) {
		Path path = Paths.get("/Users/Administrator/Downloads", "Button.txt");
		
		//문자열로 변환
		String path_to_string = path.toString();
		System.out.println("문자 열로 변환 : " + path_to_string);
		
		//경로를 URI로 변환
		URI path_to_uri = path.toUri();
		System.out.println("경로를 URI로 변환 : " + path_to_uri);
		
		//상대 경로 -> 절대 경로
		Path path_to_absolue_path = path.toAbsolutePath();
		System.out.println("상대 경로 -> 절대 경로 : " + path_to_absolue_path);
		
		//경로를 실제 경로로 변환 toRealPath()메서드는 파일이 없거나 접근 할수 없다면 IOException 예외를 던진다. 
		try {
			Path real_path = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
			System.out.println("실제 경로로 변환 : " + real_path);
		} catch (NoSuchFileException e) {
			System.out.println(e);
		}catch (IOException e) {
			System.out.println(e);
		}
		
		//경로를 파일로 변환 하기 toFile() : Path객체 -> File객체  toPath() : File객체 -> Path객체
		
		File path_to_file = path.toFile();
		Path file_to_path = path_to_file.toPath();
		
		System.out.println("Path to file name : " + path_to_file.getName());
		System.out.println("File to path : " + file_to_path.toString());
	}

}
