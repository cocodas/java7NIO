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
		//사용자 정의 속성 값  가져오기
		Path path = Paths.get(System.getProperty("user.home"), "Downloads", "Button.txt");
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		
		try {
			int size = udfav.size("file.description");
			ByteBuffer bb = ByteBuffer.allocateDirect(size); //ByteBuffer 인스턴스 생성 allocateDirect 함수의경우 지원하는 플랫폼인지 확인하고 사용
			//ByteBuffer class (Buffer 상속 받고 Comparable로 구현) : allocateDirect(), allocate(), wrap(), asReadOnlyBuffer(), get(), put(), hasArray(), array(), arrayOffset(), compact(), isDirect(), toString(), hashCode(), equals(), compareTo(), 
			udfav.read("file.description", bb);
			bb.flip();
			//put 함수를 이용해서 값을 쓰거나 SocketChannel 의 read 함수로 ByteBuffer instance 에 값을 쓰고 나서 데이터를 읽기 전에 ByteBuffer 클래스의 flip 함수를 한번 호출해야 한다.
			//flip() : limit을 position이 있던 위치로 설정 후 position을 0으로 이동 mark = -1 버퍼를 재사용 하기 위해 clear()하고 데이터를 버퍼에 쓴후 flip()으로 재사용 범위를 지정한 후 버퍼에서 데이터를 읽음.
			System.out.println(Charset.defaultCharset().decode(bb).toString());
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
