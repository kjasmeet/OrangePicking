/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orangepickinggame;
 
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;
/**
 * @author Alina
 * @author Jasmeet
 */
public class OrangePickingGame extends BasicGame
{
    //comment
    private TiledMap map;
    private Animation sprite, up, down, left, right;
    private float x = 34f, y = 34f;
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
        Image [] moveUp = {new Image("spriteUP.png"), new Image("spriteUP.png")};
        Image [] moveDown = {new Image("spriteDOWN.png"), new Image("spriteDOWN.png")};
        Image [] moveLeft = {new Image("spriteLEFT.png"), new Image("spriteLEFT.png")};
        Image [] moveRight = {new Image("spriteRIGHT.png"), new Image("spriteRIGHT.png")};
        int [] duration = {300, 300};
        up = new Animation(moveUp, duration, false);
        down = new Animation(moveDown, duration, false);
        left = new Animation(moveLeft, duration, false);
        right = new Animation(moveRight, duration, false); 
        sprite = right;
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    { 
        map.render(0,0);
        sprite.draw((int)x, (int)y);
    }
}