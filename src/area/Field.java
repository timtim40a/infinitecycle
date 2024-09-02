package area;

import entity.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Field extends GameBackground {

    public Field() {

        getSprites();
        setDefaultValues();

    }

    private void getSprites() {

        try {

            f1 = ImageIO.read(getClass().getResourceAsStream("/backgr/field1.png"));
            f2 = ImageIO.read(getClass().getResourceAsStream("/backgr/field2.png"));
            f3 = ImageIO.read(getClass().getResourceAsStream("/backgr/field3.png"));
            f4 = ImageIO.read(getClass().getResourceAsStream("/backgr/field4.png"));
            f5 = ImageIO.read(getClass().getResourceAsStream("/backgr/field5.png"));
            f6 = ImageIO.read(getClass().getResourceAsStream("/backgr/field6.png"));
            f7 = ImageIO.read(getClass().getResourceAsStream("/backgr/field7.png"));

        } catch (IOException e) {
        }
    }

    private void setDefaultValues() {

        speed = 1;
        phase = 0;
        phaseCounter = 0;

    }

    public void update(Player p) {

        speed = p.speed;

        if (speed > 1) {
            phaseCounter++;
            if (phaseCounter > 200 / p.speed) {
                phase++;
                if (phase > 3) {
                    phase = 0;
                }
                phaseCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (phase) {
            case 0 ->
                image = f1;
            case 1 ->
                image = f2;
            case 2 ->
                image = f3;
            case 3 ->
                image = f4;
            case 4 ->
                image = f5;
            case 5 ->
                image = f6;
            case 6 ->
                image = f7;
        }

        g2.drawImage(image, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT, null);
    }
}
