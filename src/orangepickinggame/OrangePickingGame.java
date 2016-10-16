/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Alina
 * @author Jasmeet
 */
public class OrangePickingGame extends StateBasedGame {

    public static final int menu = 0;
    public static final int play = 1;
    public static final int instruct = 2;
    public static final int gameOver = 3;

    public OrangePickingGame() {
        super("Pick oranges!");
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new Instructions(instruct));
        this.addState(new GameOver(gameOver));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
    }

    public static void main(String[] arguments) throws SlickException {

        AppGameContainer app = new AppGameContainer(new OrangePickingGame());
        app.setDisplayMode(630, 620, false);
        app.start();
    }
}
