/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static orangepickinggame.OrangePickingGame.gameOver;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author JasmeetKaur
 */
public class InitializeCode extends BasicGameState{
    
    private TiledMap map;
    private Animation sprite, up, down, left, right;
    int x = 20, y = 20;
    private double time;
    int xHeight = 0;
    int yHeight = 0;
    int xBox = 0;
    int yBox = 0;
    Image orange;
    Rectangle player = new Rectangle(x,y,25,25);
    int score = 0;
    int numOs = 150;
    TrueTypeFont font;
    
    /*orange collection declarations*/
    Orange o[] = new Orange[numOs];
    boolean isDrawn[] = new boolean[numOs];
    boolean isScored[] = new boolean[numOs];
    
    public InitializeCode(){
        
    }
    
    public double getTime(){
        return time;
    }
    
    public void setTime(double time){
        this.time = time; 
    }
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        map = new TiledMap("slick/testdata/map1.tmx");
        Image[] moveUp = {new Image("Images/spriteUP.png"), new Image("Images/spriteUP.png")};
        Image[] moveDown = {new Image("Images/spriteDOWN.png"), new Image("Images/spriteDOWN.png")};
        Image[] moveLeft = {new Image("Images/spriteLEFT.png"), new Image("Images/spriteLEFT.png")};
        Image[] moveRight = {new Image("Images/spriteRIGHT.png"), new Image("Images/spriteRIGHT.png")};
        int[] duration = {300, 300};
        up = new Animation(moveUp, duration, false);
        down = new Animation(moveDown, duration, false);
        left = new Animation(moveLeft, duration, false);
        right = new Animation(moveRight, duration, false);
        sprite = right;
        
        orange = new Image("Images/oranges.png");
        
        for (int i=0;i<numOs;i++){
            int low = 50;
            int high = 600;
            int xl = (int) (Math.random() * (600*5));
            int yl = (int) (Math.random() * (600*5));
            
            o[i] = new Orange(xl, yl, i);
            isDrawn[i] = true;
            isScored[i] = true;
        }
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Ubuntu-Title.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(25f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render(0, 0, yHeight, xHeight, 20, 20);
        
        sprite.draw(x, y);
        double calculateTime = time / 1000;
        if (calculateTime >= 10) {
            font.drawString(420, 20, "Time left: " + time / 1000, Color.yellow);
            grphcs.setColor(Color.yellow);
        } else {
            font.drawString(420, 20, "Time left: " + time / 1000, Color.red);
            grphcs.setColor(Color.red);
        }
        font.drawString(420, 50, "Current score: " + score, Color.yellow);
        int xx = Mouse.getX();
        int yy = Mouse.getY();
        
        for(int i=0;i<numOs;i++){
            if((isDrawn[i] ==true)&&((o[i].getXVal()/600)==xBox)&&((o[i].getYVal()/600)==yBox)){
                grphcs.drawImage(orange,(o[i].getXVal())%600,(o[i].getYVal())%600);
            }
        }

        //grphcs.drawString(xx + " " + yy, 50, 50);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        time -= i;
        
        if (time <= 0.0) {
            sbg.enterState(gameOver);
        }
        
        for(int j = 0; j<numOs; j++){
            if(player.intersects(o[j].getRectangle())){
                if(((o[j].getXVal()/600)==xBox)&&((o[j].getYVal()/600)==yBox)){
                    if(isScored[j]==true){
                        score += 1;
                        isScored[j] = false;
                    }
                    isDrawn[j] = false;
                }
            }
        }
    }
    @Override
    public void keyPressed(int key, char c) {

        switch (key) {
            case Input.KEY_RIGHT:
                sprite = right;
                
                if (yHeight < 80 && x >= 600) {
                     x = 10;
                     yHeight += 20;
                     yBox++;
                }else if(x < 600){
                    x += 20;
                    player.setX(x);
                }
                break;
            case Input.KEY_LEFT:
                sprite = left;
                if (x >= 20) {
                    x -= 20;
                    player.setX(x);
                }
                if (yHeight >= 4 && x <= 10) {
                     x = 630;
                    yHeight -= 20;
                    yBox--;
                }

                break;
            case Input.KEY_DOWN:
                sprite = down;
                
                if (y >= 600 && xHeight < 80 ) {
                    y = 20;
                    xHeight += 20;
                    xBox++;
                }else if(y < 600){
                    y += 20;
                    player.setY(y);
                }
                
                break;
            case Input.KEY_UP:
                sprite = up;
                if (y >= 20) {
                    y -= 20;
                    player.setY(y);
                }
                if (xHeight >= 4 && y <= 10) {
                    y = 610;
                    xHeight -= 20;
                    xBox--;
                }
                break;
            default:
                break;
        }
    }
    
}
