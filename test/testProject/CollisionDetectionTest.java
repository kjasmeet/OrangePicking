/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testProject;

import org.newdawn.slick.geom.Rectangle;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * this class tests the collision of one rectangle w/ another
 *
 * @author JasmeetKaur
 */
public class CollisionDetectionTest extends BasicGame {

    ArrayList<Rectangle> cyanRec = new ArrayList<>();
    ArrayList<Rectangle> pinkRec = new ArrayList<>();
    int flag = 0;

    boolean[] isDrawn = new boolean[40];

    public CollisionDetectionTest(String title) {
        super(title);
    }

    /**
     * initialize the array with randomly placed rectangles
     *
     * @param gc
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        int x, y;
        for (int m = 0; m < 10; m++) {
            x = (int) Math.floor(Math.random() * 700);
            y = (int) Math.floor(Math.random() * 500);
            cyanRec.add(new Rectangle(x, y, 20, 20));
        }
        x = (int) Math.floor(Math.random() * 700);
        y = (int) Math.floor(Math.random() * 500);
        pinkRec.add(new Rectangle(x, y, 20, 20));

    }

    /**
     * if pink rectangle hits cyan one, collision detection happens
     *
     * @param gc
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        for (int j = 0; j < pinkRec.size(); j++) {
            int xx = (int) pinkRec.get(j).getX();
            int yy = (int) pinkRec.get(j).getY();
            if (xx < 500) {
                flag = 1;
                pinkRec.get(j).setX(moveHorizontal(xx, i));
                pinkRec.get(j).setY(moveHorizontal(xx, i));

            } else if (xx >= 500) {
                flag = 2;
                pinkRec.get(j).setX(moveHorizontal(xx, i));
            }
        }
        for (int j = 0; j < cyanRec.size(); j++) {
            if (cyanRec.get(j).intersects(pinkRec.get(0))) {
                flag = 3;
            }
        }
    }

    /**
     * according to the value of flag, move the sprite
     *
     * @param delta
     */
    public int moveHorizontal(int x, int delta) {
        if (flag == 1) {
            x += delta * .9;
        } else if (flag == 2) {
            while (true) {
                x -= delta;
                if (x == 50) {
                    break;
                }
            }

        }
        return x;
    }

    /**
     * render view
     *
     * @param gc
     * @param grphcs
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        for (int i = 0; i < cyanRec.size(); i++) {
            grphcs.setColor(Color.cyan);
            grphcs.fill(cyanRec.get(i));

        }
        grphcs.setColor(Color.pink);
        grphcs.fill(pinkRec.get(0));

        if (flag == 3) {
            gc.pause();
            grphcs.drawString("Collision detected!!", 400, 550);

        }

    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new CollisionDetectionTest("Testing collision"));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
