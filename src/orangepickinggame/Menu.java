/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author JasmeetKaur
 */
public class Menu extends BasicGameState {
    Image image;
    Image play;
    Image instruction;
    public Menu(int menuState) {

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       image = new Image("Images/gameBackground.PNG");
       play = new Image("Images/play.png");
       instruction = new Image("Images/instructions.png");
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(image, 0, 0);
        g.drawString("Welcome to Orange Picking!", 50, 50);
        g.setColor(Color.black);
        play.draw(50, 150);
        instruction.draw(50, 250);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        
        if((xPos>50 && xPos<200) && (yPos>408 && yPos < 466)){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(1);
            }
        }
        
        if((xPos>50 && xPos<316) && (yPos>304 && yPos < 367)){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(2);
            }
        }
        
        
    }
}
