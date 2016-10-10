/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orangepickinggame;
 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;
/**
 * @author panos
 */
public class OrangePickingGame extends BasicGame
{
    private TiledMap map;
    public OrangePickingGame()
    {
        super("Pick oranges!");
    }
 
    public static void main(String[] arguments)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new OrangePickingGame());
            app.setDisplayMode(500, 400, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
 
    @Override
    public void init(GameContainer container) throws SlickException
    {
        map = new TiledMap("slick/testdata/map1.tmx");
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        map.render(0,0);
    }
}