package java7.nio2.chapter7.fileCopySpeedTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCopySpeedTestMain {

	public static void main(String[] args) {
		//C:\Users\Administrator\Desktop
		final Path copyFrom = Paths.get(System.getProperty("user.home"), "Desktop", "가족끼리 왜 이래.E30.141129.HDTV.H264.720p-WITH.mp4");
    	final Path copyTo = Paths.get(System.getProperty("user.home"), "TvProgram", "가족끼리 왜 이래.E30.141129.HDTV.H264.720p-WITH.mp4");
		
		
		Undirect undirect = new Undirect();
		Direct direct = new Direct();
		TransferTo transferTo = new TransferTo();
		TransferFrom transferFrom = new TransferFrom();
		Map map = new Map();
		BufferedStream bufferedStream = new BufferedStream();
		UnbufferedStream unbufferedStream = new UnbufferedStream();
		FilesCopy filesCopy = new FilesCopy();
		FilesCopy2 filesCopy2 = new FilesCopy2();
		
		undirect.undirect(copyFrom, copyTo);		
		direct.direct(copyFrom, copyTo);
		transferTo.transferTo(copyFrom, copyTo);
		transferFrom.transferFrom(copyFrom, copyTo);
		map.map(copyFrom, copyTo);
		bufferedStream.bufferedStream(copyFrom, copyTo);
		unbufferedStream.unbufferedStream(copyFrom, copyTo);
		filesCopy.fileCopy(copyFrom, copyTo);
		filesCopy2.fileCopy2(copyFrom, copyTo);
	
	}

}
