package com.sal.rain;

// on 52
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.sal.rain.entity.mob.Player;
import com.sal.rain.graphics.Screen;
import com.sal.rain.input.Keyboard;
import com.sal.rain.level.Level;
import com.sal.rain.level.RandomLevel;
import com.sal.rain.level.SpawnLevel;

public class Game extends Canvas implements Runnable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = 168;
	public static int scale = 3;
	public static String title = "Rain";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {

		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new SpawnLevel("/textures/level.png");
		player = new Player(key);

		addKeyListener(key);
	}

	public synchronized void start() {

		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 90.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "ups, " + +frames + " fps");
				frame.setTitle(title + "  |  " + updates + "ups, " + +frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {

		key.update();
		player.update();

	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - (screen.width / 2);
		int yScroll = player.y - (screen.height / 2);

		level.render(xScroll, yScroll, screen);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}

}
