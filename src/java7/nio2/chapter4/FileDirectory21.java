package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDirectory21 {

	public static void main(String[] args) {
		//�ӽ� ���丮 ���� �ϱ�
		String tmpDirPrefix = "nio_";
		
		try {
			//null ���ξ ����
			Path tmp1 = Files.createTempDirectory(null);
			System.out.println("TMP : " + tmp1.toString());
			
			//�̸� ���س��� ���ξ ����
			Path tmp2 = Files.createTempDirectory(tmpDirPrefix);
			System.out.println("TMP : " + tmp2.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//�ӽ� ���丮�� �⺻ ��ġ�� ����� �𸥴ٸ� ���� �ڵ� ���
		String defaultTmp = System.getProperty("java.io.tmpdir");
		System.out.println(defaultTmp);
	}

}
