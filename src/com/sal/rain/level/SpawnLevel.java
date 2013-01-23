package com.sal.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sal.rain.level.tile.Tile;

public class SpawnLevel extends Level {


	private int[] levelPixels;

	public SpawnLevel(String path) {

		super(path);
	}

	protected void loadLevel(String path) {

		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			tiles = new Tile[w * h];
			levelPixels = new int[w * h];
			image.getRGB(0, 0, w, h, levelPixels, 0, w);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Excetions! could not load level file!");
		}

	}

	//grass = 0xFF00
	//flower = 0x FFFF00
	//rock = 0x7f7f00
	protected void generateLevel() {

		for (int i = 0; i < levelPixels.length; i++) {
			if (levelPixels[i] == 0xFF00FF00) tiles[i] = Tile.grass;
			if (levelPixels[i] == 0xFFFFff00) tiles[i] = Tile.flower;
			if (levelPixels[i] == 0xFF7f7f00) tiles[i] = Tile.rock;

		}
	}

}
