package java7.nio2.chapter9.Asynchronous09;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ClientBasicSetting {

	final int DEFAULT_PORT = 5555;
	final String IP = "192.168.85.1";
	ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
	ByteBuffer helloBuffer = ByteBuffer.wrap("¾È³ç!!".getBytes());
	ByteBuffer randomBuffer;
	CharBuffer charBuffer;
	Charset charset = Charset.defaultCharset();
	CharsetDecoder decoder = charset.newDecoder();
}
