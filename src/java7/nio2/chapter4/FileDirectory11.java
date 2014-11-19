package java7.nio2.chapter4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileDirectory11 {

	public static void main(String[] args) {
		//write()�޼���� ���ξ���
		Path wikiPath = Paths.get(System.getProperty("user.home"), "Downloads", "wiki.txt");
		
		Charset charset = Charset.forName("UTF-8");
		//Charset class : Charset class�� �ɸ��ͼ� ��ü�� ��Ÿ����, �� Ŭ������ �ν��Ͻ��� ��Ÿ���� �ɸ��ͼ°�
		//�����ڵ� ������ ��ȯ�� ó���� �ִ� Ŭ�����̴�. Charset class�� �ν��Ͻ��� �����ڸ� ���ؼ� �������� �ʰ� static���� 
		//�����Ǵ� forName()�޼��带 ����ؼ� �����Ѵ�. ���� ���, �����ڵ�� �ƽ�Ű�ڵ� ������ ���ڵ�/���ڵ��� �ϴ� Charset �ν��Ͻ��� ������ ���� ������ �� �ִ�.
		//ex) Charset charset = Charset.forName("US-ASCII");(�����ϴ� �ɸ��ͼ� : ISO-8859-1, ISO-8859-15, US-ASCIIUTF-16, UTF-16BE, UTF-16LE, UTF-8, windows-1252)
	    //CharsetEncoder : �ɸ��͸� ����Ʈ�� ��ȯ���ִ� ���ڴ�
		//CharsetDecoder : ����Ʈ �����ͷ� ���� �ɸ��͸� �������ִ� ���ڴ�
		ArrayList<String> lines = new ArrayList<>();
		lines.add("\n");
		lines.add("���� ����");
		lines.add("����");
		lines.add("�׸�");
		lines.add("��������!!!");
		//System.out.println(lines);
		
		try {
			Files.write(wikiPath, lines, charset, StandardOpenOption.APPEND);
			//wtite(���, �����, ��ȯ�� �ɸ��� ��, OpenOption); 
			//StandardOpenOption�� ���� : APPEND, READ, WRITE, CREADTE, CREATE_NEW, DELETE_ON_CLOSE, TRUNCATE_EXISTING, SPARSE, SYNC, DSYNC
			//������ ��� �������� �Ǵ� ���� �Ұ����� ���ϴ� �ɼ�
			
		} catch (IOException e) {
			System.err.println(e);
		}
	    
	}

}
