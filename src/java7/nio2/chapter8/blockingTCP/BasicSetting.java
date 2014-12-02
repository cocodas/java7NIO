package java7.nio2.chapter8.blockingTCP;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class BasicSetting {

	final int DEFAULT_PORT = 5555;
	final String IP = "127.0.0.1";
	ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	ByteBuffer helloBuffer = ByteBuffer.wrap("Hello !".getBytes());
	ByteBuffer randomBuffer;
	CharBuffer charBuffer;
	Charset charset = Charset.defaultCharset();
	CharsetDecoder decoder = charset.newDecoder();
	
}
