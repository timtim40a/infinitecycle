package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int speed;
	public final int maxspeed = 60;
	public int acceleration;
	public int accelerationCounter;
	
	public int phase;
	public int phaseCounter;
	public BufferedImage f1, f2, f3, f4;
}
