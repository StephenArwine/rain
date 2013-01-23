package com.sal.rain.level.tile;

import com.sal.rain.graphics.Screen;
import com.sal.rain.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {

		super(sprite);

	}

	public void render(int x, int y, Screen screen) {

		screen.renderTile(x << 4, y << 4, this);
	}

}
