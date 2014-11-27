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
			//�й� �۾��� �μ��۾��� �ùķ��̼��Ϸ��� �����ϰ� ����Ѵ�.
			Thread.sleep(20000 + new Random().nextInt(30000));
			System.out.println("���� �� .... " + "[ " + doc + " ]");
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}
}
