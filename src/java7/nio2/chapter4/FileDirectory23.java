package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory23 {

	public static void main(String[] args) {
		//종료 후크로 임시 디렉터리 삭제 하기
		final Path basedir = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "tmp");
		final String tmpDirPrefix = "rafa_";
		
		try {
			//지정한 디렉터리(basedir)에 임시 디렉터리(tmpDir)를 생성 한다.
			final Path tmpDir = Files.createTempDirectory(basedir, tmpDirPrefix);
			System.out.println("임시 디렉터리 생성 : " + tmpDir);
			
			Runtime.getRuntime().addShutdownHook(new Thread(){
				public void run() {
					System.out.println("임시 디렉터리 폴더를 삭제중...");
					try {
						//10초동안 대기 하는 방법으로 임시 파일의 I/O 처리를 시뮬레이션
						//시간이 만료 되면 임시 파일은 삭제된다.
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						System.err.println(e1);
					} //작업 완료
					
					try (DirectoryStream<Path> ds = Files.newDirectoryStream(tmpDir)){
						for (Path file : ds) {
							Files.delete(file);//tmp폴더 안에 있는 임시 디렉터리 삭제
						}
						
						Files.delete(tmpDir); //임시 디렉터리 삭제 후 폴더 삭제 임시 디렉터리가 존재 하면 삭제 할수 없기 때문에
						
					} catch (IOException e) {
						System.err.println(e);
					}
					
					System.out.println("Shutdown-hook 완료...");
				}
			});
			
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

