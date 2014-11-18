package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileDirectory08 {

	public static void main(String[] args) {
		//사용자 정의 필터를 적용해 내용 나열하기
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		
		//디렉토리만 수락 하는 필터
		DirectoryStream.Filter<Path> dirFilter = new DirectoryStream.Filter<Path>() {
			
			@Override
			public boolean accept(Path path) throws IOException {
				return (Files.isDirectory(path, NOFOLLOW_LINKS));
			}
		};
		
		//200KB보다 큰 파일 / 디렉터리만 수락하는 필터
		DirectoryStream.Filter<Path> sizeFilter = new DirectoryStream.Filter<Path>() {

			@Override
			public boolean accept(Path path) throws IOException {
				// TODO Auto-generated method stub
				return (Files.size(path) > 204800L);
			}
		};
		
		//현제 날짜로 수정된 파일만 수락하는 필터
		DirectoryStream.Filter<Path> timeFilter = new DirectoryStream.Filter<Path>() {

			@Override
			public boolean accept(Path path) throws IOException {
				long currentTime = FileTime.fromMillis(System.currentTimeMillis()).to(TimeUnit.DAYS);
				long modifiedTime = ((FileTime) Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS)).to(TimeUnit.DAYS);
				
				if (currentTime == modifiedTime) {
					return true;
				}
				return false;
			}
		};
		
		//숨겨진 파일/디렉터리만 수락하는 필터
		DirectoryStream.Filter<Path> hiddenFilter = new DirectoryStream.Filter<Path>() {

			@Override
			public boolean accept(Path path) throws IOException {
				return (Files.isHidden(path));
			}
		};
		
		System.out.println("----- 사용자 정의 필터 적용 ----- ");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, dirFilter)){
			System.out.println("*** 디렉터리 필터 ****");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, sizeFilter)){
			System.out.println("*** 200KB보다 큰 파일 / 디렉토리 ***");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, timeFilter)){
			System.out.println("*** 현재 날짜로 수정된 파일만 걸러내는 필터 ***");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, hiddenFilter)){
			System.out.println("*** 숨겨진 파일/디렉터리만 걸러내는 필터 *** ");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
