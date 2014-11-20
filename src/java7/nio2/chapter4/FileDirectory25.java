package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory25 {

	public static void main(String[] args) {
		//�ӽ� ���� ���� �ϱ�
		String tmpFilePrefix = "rafa_";
		String tmpFileSufix = ".txt";
		
		try {
			//���ξ� ���̾ null����
			Path tmp1 = Files.createTempFile(null, null);
			System.out.println("TMP : " + tmp1.toString());
			
			//���ξ� ���̾� ����
			Path tmp2 = Files.createTempFile(tmpFilePrefix, tmpFileSufix);
			System.out.println("TMP : " + tmp2.toString());
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}

}
