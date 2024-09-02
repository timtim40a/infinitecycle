package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.keyH = keyH;
        setDefaultValues();
        getSprites();

    }

    private void getSprites() {

        try {

            f1 = ImageIO.read(getClass().getResourceAsStream("/player/cyclist1.png"));
            f2 = ImageIO.read(getClass().getResourceAsStream("/player/cyclist2.png"));
            f3 = ImageIO.read(getClass().getResourceAsStream("/player/cyclist3.png"));
            f4 = ImageIO.read(getClass().getResourceAsStream("/player/cyclist4.png"));

        } catch (IOException e) {
        }

    }

    private void setDefaultValues() {

        speed = 1;
        acceleration = 0;
        x = 460;
        y = 400;
        phase = 0;
        phaseCounter = 0;

    }

    public void update() {

        accelerationCounter++;

        if (speed > 1) {
            phaseCounter++;

            if (phaseCounter > 200 / speed) {
                phase++;
                if (phase > 3) {
                    phase = 0;
                }
                phaseCounter = 0;
            }
        }

        if (accelerationCounter > 30 / speed) {

            accelerationCounter = 0;
            acceleration = 0;

            if (keyH.forwardPressed && speed < maxspeed) {
                acceleration = 1;

            }

            if (!keyH.forwardPressed && speed > 1) {
                acceleration = -1;
            }

            speed += acceleration;

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        String accelerationStat = null;
        String forward;

        switch (phase) {
            case 0 ->
                image = f1;
            case 1 ->
                image = f2;
            case 2 ->
                image = f3;
            case 3 ->
                image = f4;
        }

        switch (acceleration) {
            case 1 ->
                accelerationStat = "accelerating";
            case -1 ->
                accelerationStat = "decelerating";
            case 0 ->
                accelerationStat = "no speed changes";
        }

        if (keyH.forwardPressed) {
            forward = "keyH.forwardPressed is true";
        } else {
            forward = "keyH.forwardPressed is false";
        }

//		if(!phase) {
//			g2.setColor(Color.white);
//		} else if(acceleration > 0) {
//			g2.setColor(Color.red);
//		} else if(acceleration < 0) {
//			g2.setColor(Color.cyan);
//		} else {
//			g2.setColor(Color.gray);
//		}
        g2.drawImage(image, x, y, 64, 128, null);
        g2.setColor(Color.white);
        g2.drawString(accelerationStat, 50, 50);
        g2.drawString(forward, 50, 62);
        g2.drawString("speed is " + speed, 50, 74);

    }

}
