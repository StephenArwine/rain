package com.sal.rain.entity;

import java.util.Random;

import com.sal.rain.graphics.Screen;
import com.sal.rain.level.Level;

public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update() {

	}

	public void render(Screen screen) {

	}

	public void remove() {

		// Remove from level
		removed = true;
	}

	public boolean isRemoved() {

		return removed;

	}

}
