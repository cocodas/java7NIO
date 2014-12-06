package java7.nio2.chapter9.Asynchronous10;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ClientBasicSetting {
	
	final int DEFAULT_PORT = 5555;
	final String IP = "127.0.0.1";
	
	final ByteBuffer helloBuffer = ByteBuffer.wrap("¾È³ç!!".getBytes());
	final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	CharBuffer charBuffer = null;
	ByteBuffer randomBuffer;
	final Charset charset = Charset.defaultCharset();
	final CharsetDecoder decoder = charset.newDecoder();
	

}
