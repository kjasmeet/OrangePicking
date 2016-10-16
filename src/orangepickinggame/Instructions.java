/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author JasmeetKaur
 */
public class Instructions extends BasicGameState {

    Image background;
    Image back;
    public Instructions(int instructions) {
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("Images/patterns.PNG");
        back = new Image("Images/back.PNG");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background, 0, 0);
        g.drawString("Move your arrows keys to move up, down, left or right.", 50, 50);
        g.drawString("Use your spacebar key to pick up oranges.", 50, 100);
        g.drawString("Meet the goal for the level to pass on to the next level.", 50, 150);
        g.drawString("Hit the back button to go to menu!", 50, 200);
        g.drawImage(back, 225, 400);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
       
        
        if((xPos>224 && xPos<367) && (yPos>150 && yPos < 219)){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(0);
            }
        }
    }
    
}
