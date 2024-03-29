package com.sal.rain.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	
	public static Sprite VoidSprite = new Sprite(16, 0x1B87E0);
	
	public static Sprite player_Forward = new Sprite(32,0,5,SpriteSheet.tiles);
	public static Sprite player_Left = new Sprite(32,1,5,SpriteSheet.tiles);
	public static Sprite player_Facing = new Sprite(32,2,5,SpriteSheet.tiles);
	public static Sprite player_Right = new Sprite(32,3,5,SpriteSheet.tiles);
	
public static Sprite player_Forward_1= new Sprite(32,0,6,SpriteSheet.tiles);
public static Sprite player_Forward_2= new Sprite(32,0,7,SpriteSheet.tiles);

public static Sprite player_Left_1= new Sprite(32,1,6,SpriteSheet.tiles);
public static Sprite player_Left_2= new Sprite(32,1,7,SpriteSheet.tiles);

public static Sprite player_Facing_1= new Sprite(32,2,6,SpriteSheet.tiles);
public static Sprite player_Facing_2= new Sprite(32,2,7,SpriteSheet.tiles);

public static Sprite player_Right_1= new Sprite(32,3,6,SpriteSheet.tiles);
public static Sprite player_Right_2= new Sprite(32,3,7,SpriteSheet.tiles);
	
public Sprite(int size, int x, int y, SpriteSheet sheet) {

		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();

	}

	public Sprite(int size, int color) {

		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);

	}

	private void setColor(int color) {

		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;

		}

	}

	private void load() {

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
