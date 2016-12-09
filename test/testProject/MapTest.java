/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testProject;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * this class pulls up a random map
 *
 * @author JasmeetKaur
 */
public class MapTest extends BasicGame {

    TiledMap map;

    public MapTest(String title) {
        super(title);
    }

    /**
     * initialize the map
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("slick/testdata/map1.tmx");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
    }

    /**
     * render the map on screen at location 0,0
     * @param gc
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        map.render(0, 0);
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new MapTest("Testing map"));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
