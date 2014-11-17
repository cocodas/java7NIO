package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileStore01 {

	public static void main(String[] args) {
		
		//모든 파일 저장소의 속성 가져오기.
		FileSystem fs = FileSystems.getDefault();
		for (FileStore store : fs.getFileStores()) {
			try {
				long totalSpace = store.getTotalSpace() / 1024 ;
				long usedSpace = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
				long availableSpace = store.getUsableSpace() / 1024;
				boolean isReadOnly = store.isReadOnly();
				
				System.out.println("--- " + store.name() + " --- " + store.type());
				System.out.println("전체 공간 : " + totalSpace);
				System.out.println("사용 공간 : " + usedSpace);
				System.out.println("사용 가능 공간 : " + availableSpace);
				System.out.println("읽기 전용 입니까? " + isReadOnly);
				
			} catch (IOException e) { 
				System.err.println(e);
			}
		}

	}

}
