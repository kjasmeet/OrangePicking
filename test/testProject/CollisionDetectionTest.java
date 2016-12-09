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
 * this class tests the mouse input collision with 
 * the rectangles drawn
 * @author JasmeetKaur
 */
public class CollisionDetectionTest extends BasicGame {

    ArrayList<Rectangle> arrayRec = new ArrayList<>();
    boolean[] isDrawn = new boolean[40];

    public CollisionDetectionTest(String title) {
        super(title);
    }

    /**
     * initialize the array with randomly placed rectangles
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        for (int m = 0; m < 40; m++) {
            int x = (int) Math.floor(Math.random() * 700);
            int y = (int) Math.floor(Math.random() * 500);
            System.out.println(x + " " + y);
            arrayRec.add(new Rectangle(x, y, 20, 20));
            isDrawn[m] = true;
        }
    }

    /**
     * if the user clicks on the rectangle, it will disappear
     * @param gc
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        for (int j = 0; j < arrayRec.size(); j++) {
            if (Mouse.isButtonDown(0)) {
                int xx = Mouse.getX();
                int yy = Mouse.getY();
                yy = 600 - yy;
                if (xx >= arrayRec.get(j).getMinX() && xx <= arrayRec.get(j).getMaxX()) {
                    if (yy >= arrayRec.get(j).getMinY() && yy <= arrayRec.get(j).getMaxY()) {
                        isDrawn[j] = false;
                    }
                }
            }
        }
    }

    /**
     * render view
     * @param gc
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        for (int i = 0; i < arrayRec.size(); i++) {
            if (isDrawn[i]) {
                grphcs.setColor(Color.cyan);
                grphcs.fill(arrayRec.get(i));
            }

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
