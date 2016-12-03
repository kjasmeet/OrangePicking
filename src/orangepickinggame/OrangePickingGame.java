/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The main class; this starts everything
 * @author Alina
 * @author Jasmeet
 */
public class OrangePickingGame extends StateBasedGame {

    //all the variables are initialized; this keeps the code clean
    public static final int menu = 0;
    public static final int play = 1;
    public static final int instruct = 2;
    public static final int gameOver = 3;
    public static final int playLevelTwo = 4;
    public static final int playLevelThree = 5;
    public static final int splashScreen = 6;
    public static final int congrats = 7;
    public static int state = 0;

    /**
     * in the constructor of class, all the states are added to initialize them
     */
    public OrangePickingGame() {
        super("Pick oranges!");
        /* adds all states for game*/
        this.addState(new Menu(menu)); /* menu screen (can choose instructions or gameplay*/
        this.addState(new Play(play)); /* level 1*/
        this.addState(new Instructions(instruct)); /* instructions for game */
        this.addState(new GameOver(gameOver)); /* game over screen*/
        this.addState(new PlayLevelTwo(playLevelTwo)); /* level 2 */
        this.addState(new PlayLevelThree(playLevelThree)); /* level 3 */
        this.addState(new SplashScreen(splashScreen)); /* transitional splash screen*/
        this.addState(new Congratulations(congrats));
    }

    /**
     * this method is set to present the menu screen first when we run the project
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        /* initializes states*/
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this); 
        this.enterState(menu);
    }

    /**
     * load the game
     * @param arguments
     * @throws SlickException 
     */
    public static void main(String[] arguments) throws SlickException {
        AppGameContainer app = new AppGameContainer(new OrangePickingGame());
        app.setDisplayMode(620, 630, false);
        app.start();
    }
}
