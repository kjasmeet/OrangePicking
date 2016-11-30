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
import java.text.DecimalFormat;
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
    
    /* tmx map that will serve as background for all levels*/
    private TiledMap map;
    /* animated sprite - images for each of the 4 directions so it looks like it is turning*/
    private Animation sprite, up, down, left, right;
    /*start coordinates for the sprite*/
    int x = 20, y = 20;
    /* time limit for level*/
    private double time;
    int xHeight = 0;
    int yHeight = 0;
    /*current coordinate squares of the map (there are 5 in each direction)*/
    int xBox = 0;
    int yBox = 0;
    /* orange image to be placed for collection*/
    Image orange;
    /*defines a rectangle for sprite to be used in collision detection*/
    Rectangle player = new Rectangle(x,y,25,25);
    /*score of the current level*/
    int score = 0;
    /* number of oranges to be placed for collection*/
    int numOs = 150;
    /* font for messages to be displayed*/
    TrueTypeFont font;
    /*get highscore */
    static int highscore;
    
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    int keyPress = RIGHT;
    
    /*orange collection declarations*/
    Orange o[] = new Orange[numOs]; /* array of orange objects for whole map*/
    boolean isDrawn[] = new boolean[numOs]; /* array of booleans to mark if orange is displayed (will be false if picked up already*/
    boolean isScored[] = new boolean[numOs]; /* array of boolean to mark if score has been tallied for this orange*/
    
    public InitializeCode(){
        
    }
    
    /*gets time limit*/
    public double getTime(){
        return time;
    }
    
    /* sets time limit*/
    public void setTime(double time){
        this.time = time; 
    }
    
    @Override
    public int getID() {
        return 0;
    }

    /* initializes level*/
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        /*loads map*/
        map = new TiledMap("slick/testdata/map1.tmx");
        
        /*declares images for each direction for the sprite and adds as animations */
        Image[] moveUp = {new Image("Images/spriteUP.png"), new Image("Images/spriteUP.png")};
        Image[] moveDown = {new Image("Images/spriteDOWN.png"), new Image("Images/spriteDOWN.png")};
        Image[] moveLeft = {new Image("Images/spriteLEFT.png"), new Image("Images/spriteLEFT.png")};
        Image[] moveRight = {new Image("Images/spriteRIGHT.png"), new Image("Images/spriteRIGHT.png")};
        int[] duration = {300, 300};
        up = new Animation(moveUp, duration, false);
        down = new Animation(moveDown, duration, false);
        left = new Animation(moveLeft, duration, false);
        right = new Animation(moveRight, duration, false);
        /* declares initial direction of the sprite as right*/
        sprite = right;
        
        /*loads orange image from .png file*/
        orange = new Image("Images/oranges.png");
        
        /*loops through array to create numOs number of oranges and randomly place on map*/
        for (int i=0;i<numOs;i++){
            int xl = (int) (Math.random() * (600*5));
            int yl = (int) (Math.random() * (600*5));
            
            o[i] = new Orange(xl, yl, i);
            isDrawn[i] = true;
            isScored[i] = true;
        }
        
        changeFont();
        
    }

    /* renders graphics for the level*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        /* draws map*/
        map.render(0, 0, yHeight, xHeight, 20, 20);
        
        /* draw sprite at current coordinates*/
        sprite.draw(x, y);
        
        /*calculates time limit, displays to user*/
        double calculateTime = time / 1000;
        DecimalFormat df = new DecimalFormat("#");
        if (calculateTime >= 10) {
            font.drawString(420, 20, "Time left: " + df.format(calculateTime), Color.yellow);
            grphcs.setColor(Color.yellow);
        } else {
            font.drawString(420, 20, "Time left: " + df.format(calculateTime), Color.red);
            grphcs.setColor(Color.red);
        }
        
        /* draws current score*/
        font.drawString(420, 50, "Current score: " + score, Color.yellow);
        //int xx = Mouse.getX();
        //int yy = Mouse.getY();
        
        /* only draws oranges if they are members of the current coordinate square of the map*/
        for(int i=0;i<numOs;i++){
            if((isDrawn[i] ==true)&&((o[i].getXVal()/600)==xBox)&&((o[i].getYVal()/600)==yBox)){
                grphcs.drawImage(orange,(o[i].getXVal())%600,(o[i].getYVal())%600);
            }
        }

        //grphcs.drawString(xx + " " + yy, 50, 50);
    }

    /* updates game as changes are made */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        /* updates time. if time is up, moves to gameOver state*/
        time -= i;
        if (time <= 0.0) {
            highscore += score;
            sbg.enterState(gameOver);
        }
        
        /* updates orange objects to indicate picked up if a collision with the sprite is detected*/
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
    
    /* updates position and direction of sprite if a key press is detected. 
       Also updates the current coordinate square of the map if applicable.
    */
    @Override
    public void keyPressed(int key, char c) {
        
        switch (key) {
            case Input.KEY_RIGHT:
                sprite = right;
                keyPress = RIGHT;
                if (yHeight < 80 && x >= 590) {
                     x = 10;
                     yHeight += 20;
                     yBox++;
                }else if(x < 590){
                    x += 20;
                    player.setX(x);
                }
                break;
            case Input.KEY_LEFT:
                keyPress = LEFT;
                sprite = left;
                if (x >= 20) {
                    x -= 20;
                    player.setX(x);
                }
                if (yHeight >= 4 && x <= 10) {
                    x = 590;
                    yHeight -= 20;
                    yBox--;
                }

                break;
            case Input.KEY_DOWN:
                keyPress = DOWN;
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
                keyPress = UP;
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
    
    /* font for displayed messages*/
    public void changeFont(){
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Ubuntu-Title.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(25f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setHighscore(int highscore){
        this.highscore = highscore;
    }
    
    public int getHighscore(){
        return highscore;
    }
    
    public int addScore(int currentScore){
        highscore = highscore + currentScore;
        return highscore;
    }
    
}
