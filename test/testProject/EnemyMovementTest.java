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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * this class tests how basic sprite images move
 * @author JasmeetKaur
 */
public class EnemyMovementTest extends BasicGame{

    Image butterfly;
    int x = 0, y = 0;
    int move = 0; 
    int flag = 0;
    
    public EnemyMovementTest(String title) {
        super(title);
    }

    /**
     * initialization of butterfly image
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        butterfly = new Image("TestImages/butterfly1.png");
    }

    /**
     * according to the value of x, set flags
     * @param gc
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if(x < 500){
            flag = 1;
            moveHorizontal(i);
        }else if(x >= 500){
            flag = 2;
            moveHorizontal(i);
        }
        
        System.out.println(x);
    }

    /**
     * draw on screen
     * @param gc
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        butterfly.draw(x, 400);
        
    }
    
    /**
     * according to the value of flag, move the sprite
     * @param delta 
     */
    public void moveHorizontal(int delta){
        if(flag == 1){
            x+=delta * .5f;
        }else if( flag == 2){
            while(true){
                 x-=delta;
                 if(x == 50){
                     break;
                 }
            }
           
        }
    }
    
    /**
     * start of program
     * @param argv 
     */
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new EnemyMovementTest("Testing enemy movement"));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
}
