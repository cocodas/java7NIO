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
		//����� ���� ���͸� ������ ���� �����ϱ�
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		
		//���丮�� ���� �ϴ� ����
		DirectoryStream.Filter<Path> dirFilter = new DirectoryStream.Filter<Path>() {
			
			@Override
			public boolean accept(Path path) throws IOException {
				return (Files.isDirectory(path, NOFOLLOW_LINKS));
			}
		};
		
		//200KB���� ū ���� / ���͸��� �����ϴ� ����
		DirectoryStream.Filter<Path> sizeFilter = new DirectoryStream.Filter<Path>() {

			@Override
			public boolean accept(Path path) throws IOException {
				// TODO Auto-generated method stub
				return (Files.size(path) > 204800L);
			}
		};
		
		//���� ��¥�� ������ ���ϸ� �����ϴ� ����
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
		
		//������ ����/���͸��� �����ϴ� ����
		DirectoryStream.Filter<Path> hiddenFilter = new DirectoryStream.Filter<Path>() {

			@Override
			public boolean accept(Path path) throws IOException {
				return (Files.isHidden(path));
			}
		};
		
		System.out.println("----- ����� ���� ���� ���� ----- ");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, dirFilter)){
			System.out.println("*** ���͸� ���� ****");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, sizeFilter)){
			System.out.println("*** 200KB���� ū ���� / ���丮 ***");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, timeFilter)){
			System.out.println("*** ���� ��¥�� ������ ���ϸ� �ɷ����� ���� ***");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, hiddenFilter)){
			System.out.println("*** ������ ����/���͸��� �ɷ����� ���� *** ");
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
