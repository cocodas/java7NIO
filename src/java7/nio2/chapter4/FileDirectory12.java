package java7.nio2.chapter4;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class FileDirectory12 {

	public static void main(String[] args) {
		//readAllBytes() 메서드 읽기
		Path ballPath = Paths.get(System.getProperty("user.home"),"Downloads","ball.png");

		try {
			byte[] ballArray = Files.readAllBytes(ballPath);
			//ImageIO.write()를 사용하여 쓰기
			//BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(ballArray));
			//ImageIO.write(bufferedImage, "png", (ballPath.resolveSibling("bytesToBall.png")).toFile();
			
			Files.write(ballPath.resolveSibling("bytesToBall.png"), ballArray);
			//resolve : 경로조합
			//resolveSibling : 형제파일접근
			//System.out.println(ballArray);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
