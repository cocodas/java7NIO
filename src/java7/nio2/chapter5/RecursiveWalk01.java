package java7.nio2.chapter5;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class RecursiveWalk01 {

	public static void main(String[] args) {
		//재귀 처리
		//파일 트리 시작 위치 지정
		Path listDir = Paths.get(System.getProperty("user.home"), "Downloads");
		
		//walk 인스턴스 생성
		ListTree walk = new ListTree();
		
		//링크 FOLLOW
		EnumSet option = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		
		
		try {
			//walk 시작
			Files.walkFileTree(listDir, option, Integer.MAX_VALUE, walk);
			//walkFileTree(시작위치, walk에 지정할 옵션, 방문할 최대 디렉터리 레벨의 수 (모든 디렉터리 순회 : Intrger.MAX_VALUE), walk 인스턴스);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
