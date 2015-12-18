package main.java.service;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateImage {
	private static final Logger log = LogManager.getLogger();

	public CreateImage() {
	}

	public void crateImage(String text, String fileName) {
		log.info("Creating image");
		File imageStart = new File("D:\\GitHub\\Tsvetkov\\WebTask\\image.jpg");
		File file = new File("D:\\GitHub\\Tsvetkov\\WebTask\\WebContent\\".concat(fileName.concat(".jpg")));
		try {
			BufferedImage image = ImageIO.read(imageStart);
			Graphics graphics = image.getGraphics();

			graphics.setFont(graphics.getFont().deriveFont(30f));
			graphics.drawString(text, 20, 40);
			graphics.dispose();
			ImageIO.write(image, "jpg", file);
			log.info(file.getAbsolutePath() + " created image PATH");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
