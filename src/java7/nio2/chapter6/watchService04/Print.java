package java7.nio2.chapter6.watchService04;

import java.nio.file.Path;
import java.util.Random;

public class Print implements Runnable {

	private Path doc;
	
	public Print(Path doc) {
		this.doc = doc;
	}
	@Override
	public void run() {
		try {
			//분배 작업과 인쇄작업을 시뮬레이션하려고 랜덤하게 대기한다.
			Thread.sleep(20000 + new Random().nextInt(30000));
			System.out.println("복사 중 .... " + "[ " + doc + " ]");
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
}
