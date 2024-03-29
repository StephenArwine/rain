package com.sal.rain.entity.mob;

import com.sal.rain.graphics.Screen;
import com.sal.rain.graphics.Sprite;
import com.sal.rain.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	public Player(Keyboard input) {

		this.input = input;
		sprite = Sprite.player_Forward;

	}

	public Player(int x, int y, Keyboard input) {

		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void update() {

		int xa = 0, ya = 0;
		if (anim < 7500) anim++;
		else anim = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa++;
		if (input.right) xa--;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void render(Screen screen) {

		if (dir == 0) {
			sprite = Sprite.player_Forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_Forward_1;
				} else {
					sprite = Sprite.player_Forward_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_Facing;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_Facing_1;
				} else {
					sprite = Sprite.player_Facing_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_Left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_Left_1;
				} else {
					sprite = Sprite.player_Left_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_Right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_Right_1;
				} else {
					sprite = Sprite.player_Right_2;
				}
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite);

	}
}
