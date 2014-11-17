package java7.nio2.chapter2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserDefinedFileAttributeView04 {

	public static void main(String[] args) {
		//����� ���� �Ӽ� ��  ��������
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "Button.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		
		try {
			int size = udfav.size("file.description");
			ByteBuffer bb = ByteBuffer.allocateDirect(size); //ByteBuffer �ν��Ͻ� ���� allocateDirect �Լ��ǰ�� �����ϴ� �÷������� Ȯ���ϰ� ���
			//ByteBuffer class (Buffer ��� �ް� Comparable�� ����) : allocateDirect(), allocate(), wrap(), asReadOnlyBuffer(), get(), put(), hasArray(), array(), arrayOffset(), compact(), isDirect(), toString(), hashCode(), equals(), compareTo(), 
			udfav.read("file.description", bb);
			bb.flip();
			//put �Լ��� �̿��ؼ� ���� ���ų� SocketChannel �� read �Լ��� ByteBuffer instance �� ���� ���� ���� �����͸� �б� ���� ByteBuffer Ŭ������ flip �Լ��� �ѹ� ȣ���ؾ� �Ѵ�.
			//flip() : limit�� position�� �ִ� ��ġ�� ���� �� position�� 0���� �̵� mark = -1 ���۸� ���� �ϱ� ���� clear()�ϰ� �����͸� ���ۿ� ���� flip()���� ���� ������ ������ �� ���ۿ��� �����͸� ����.
			System.out.println(Charset.defaultCharset().decode(bb).toString());
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
