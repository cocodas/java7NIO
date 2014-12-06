package java7.nio2.chapter9.Asynchronous04;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsynchronousCompletionHandler {
	
	//���� �б�� completionHandler
	
	static Thread current;
	ByteBuffer buffer = ByteBuffer.allocate(10000);
	
	public void completionHander(Path path) {
	
		try {
			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			
			current = Thread.currentThread();
			asynchronousFileChannel.read(buffer, 0, "�б� �۾� ��...", new CompletionHandler<Integer, Object>(){

				@Override
				public void completed(Integer result, Object attachment) {
					System.out.println(attachment);
					System.out.print("Read bytes : " + "[ " + result + " ]");
					current.interrupt();
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					System.out.println(attachment);
					System.out.println("Error : " + "[ " + exc + " ]");
					current.interrupt();
				}
			});
			
			System.out.println("\n�б� �۾��� ������ ���� ��ٷ� �ֽʽÿ�...\n");
			try {
				current.join();
			} catch (InterruptedException e) {
			}
			
			//���� ���ۿ��� �о� ���� ����Ʈ�� ��� �ִ�.
			System.out.println("\n\n��� �͵��� ������ ���̹���...");

		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
