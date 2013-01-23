package com.sal.rain.level.tile;

import com.sal.rain.graphics.Screen;
import com.sal.rain.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile VoidTile = new VoidTile(Sprite.VoidSprite);

	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}

}
