package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.omg.SendingContext.RunTime;

public class FileDirectory27 {
	public static void main(String[] args) {
		//종료 후크로 임시 파일 삭제하기
		
		Path basedir = Paths.get(System.getProperty("user.home"),"Downloads", "tmp");
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			final Path tmpFile = Files.createTempFile(basedir, tmpFilePrefix, tmpFileSufix);
			System.out.println("파일 이름 : " + tmpFile.toString() + " 생성");
			
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					System.out.println("*** 임시 파일 삭제 중 ***");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.err.println(e);				
					}
					try {
						Files.delete(tmpFile);
					} catch (IOException e1) {
						System.err.println(e1);
					}
					
					System.out.println("*** 임시 파일 삭제 완료!! ***");
					
				}
				
			});
		} catch (IOException e2) {
			System.err.println(e2);
		}
		
	}

}
