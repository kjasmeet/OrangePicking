/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testProject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This test class tests a basic animation of penguin sprite basic functionality
 * involves: moving up, down, left, and right
 *
 * @author JasmeetKaur
 */
public class AnimationTest extends BasicGame {

    Animation up, down, left, right, sprite;
    int x, y;

    public AnimationTest(String title) {
        super(title);
    }

    /**
     * initializes everything in order to be used later
     *
     * @param gc
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        Image[] penguinUP = {new Image("TestImages/PenguinUP.PNG"), new Image("TestImages/PenguinUP.PNG")};
        Image[] penguinDOWN = {new Image("TestImages/PenguinDOWN.PNG"), new Image("TestImages/PenguinDOWN.PNG")};
        Image[] penguinLEFT = {new Image("TestImages/PenguinLEFT.PNG"), new Image("TestImages/PenguinLEFT.PNG")};
        Image[] penguinRIGHT = {new Image("TestImages/PenguinRIGHT.PNG"), new Image("TestImages/PenguinRIGHT.PNG")};

        int[] duration = {300, 300};
        up = new Animation(penguinUP, duration, false);
        down = new Animation(penguinDOWN, duration, false);
        left = new Animation(penguinLEFT, duration, false);
        right = new Animation(penguinRIGHT, duration, false);

        sprite = right;

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
    }

    /**
     * testing the view for the user
     *
     * @param gc
     * @param grphcs
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        sprite.draw(x, y);
        grphcs.drawString("Penguin(" + x + ", " + y + ")", 550, 20);
        grphcs.drawString("Move your arrow keys to move the penguin", 200, 580);
    }

    /**
     * This sets up the movement of the sprite; the sprite is supposed to stay
     * in the container whose height is 600 and width is 800;
     *
     * @param key
     * @param c
     */
    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_RIGHT:
                sprite = right;
                if (x < 690) {
                    x += 10;
                }

                break;
            case Input.KEY_LEFT:
                sprite = left;
                if (x > 0) {
                    x -= 10;
                }

                break;
            case Input.KEY_DOWN:
                sprite = down;
                if (y < 500) {
                    y += 10;
                }

                break;
            case Input.KEY_UP:
                sprite = up;
                if (y > 0) {
                    y -= 10;
                }
                break;
            default:
                break;
        }
    }

    /**
     * The game container's height and width is set in this method and this is
     * the start of the program
     *
     * @param argv
     */
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new AnimationTest("Testing Movement"));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
