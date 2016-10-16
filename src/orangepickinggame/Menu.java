/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import static orangepickinggame.OrangePickingGame.instruct;
import static orangepickinggame.OrangePickingGame.play;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
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
public class Menu extends BasicGameState {

    Image image;
    Image playImage;
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
        playImage = new Image("Images/play.png");
        instruction = new Image("Images/instructions.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(image, 0, 0);
        g.drawString("Welcome to Orange Picking!", 50, 50);
        g.setColor(Color.black);
        playImage.draw(50, 150);
        instruction.draw(50, 250);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        
        sbg.enterState(goToNextState(xPos, yPos));
        
    }

    /**
     * this method decides what state to be returned based on user's 
     * @param xPos
     * @param yPos
     * @return 
     */
    public int goToNextState(int xPos, int yPos) {
        int stateToBeReturned = 0;
        
        /*user clicked play button*/
        if ((xPos > 50 && xPos < 200) && (yPos > 408 && yPos < 466)) {
            if (Mouse.isButtonDown(0)) {
                stateToBeReturned = play;

            }
        }

        /*user clicked instruction button*/
        if ((xPos > 50 && xPos < 316) && (yPos > 304 && yPos < 367)) {
            if (Mouse.isButtonDown(0)) {
                stateToBeReturned = instruct;

            }
        }
        return stateToBeReturned;
    }
}
