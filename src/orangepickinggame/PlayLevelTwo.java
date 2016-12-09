package orangepickinggame;

import static orangepickinggame.OrangePickingGame.playLevelTwo;
import static orangepickinggame.OrangePickingGame.splashScreen;
import static orangepickinggame.OrangePickingGame.gameOver;
import static orangepickinggame.OrangePickingGame.state;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author JasmeetKaur
 */
/* Level 2 - will eventually contain version of level in which enemies can kill you. Will include 3 lives
 Increased difficulty in orange collection goal and time limit
 */
public class PlayLevelTwo extends InitializeCode {
    /* methods are defined, for the most part, in InitializeCode. This extends.*/

    int orangeGoal = 7;
    double lives;
    int numEnemies = 100;
    int flag = 0;
    int update = 0;

    private Animation enemy;

    Enemy e[] = new Enemy[numEnemies];

    /* constructor for level*/
    public PlayLevelTwo(int playState) {
        super();
    }

    /* enter level, sets time limit*/
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        super.setTime(45000);
        super.score = 0;
        super.x = 0;
        super.y = 0;
        super.xHeight = 0;
        super.yHeight = 0;

        lives = 3000;

    }

    @Override
    public int getID() {
        return playLevelTwo;
    }

    /*initialize level*/
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);

        Image[] en = {new Image("Images/butterfly1.png"), new Image("Images/butterfly1.png")};
        int[] duration = {300, 300};
        enemy = new Animation(en, duration, false);

        for (int i = 0; i < numEnemies; i++) {
            int enemyValX = (int) ((Math.random() * (600 * 5)) + 20);
            int enemyValY = (int) ((Math.random() * (600 * 5)) + 20);
            int xVal = enemyValX / 610;
            int xxBox = xVal;
            xVal *= 610;
            enemyValX -= xVal;
            int yVal = enemyValY / 620;
            int yyBox = yVal;
            yVal *= 620;
            enemyValY -= yVal;
            // System.out.printf("%d\t %d\t %d\t %d\t", enemyValX, enemyValY, xVal, yVal );
            e[i] = new Enemy(enemyValX, enemyValY, xxBox, yyBox, i, enemy);
        }
    }

    /* render graphics for level*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(420, 80, "Lives left: " + lives / 1000, Color.yellow);
        font.drawString(50, 20, "Level 2 - Avoid the enemies!" + update, Color.yellow);

        /**
         * this loop divides numEnemies by 3 so that 1/3 of them
         * can go up and down, 1/3 of them can go left and right, and the rest can go
         * diagonal
         */
        for (int i = 0; i < numEnemies; i++) {
            if (i % 3 == 0) {
                if ((e[i].getXBox() == xBox) && (e[i].getYBox() == yBox)) {
                    e[i].getAnim().draw(e[i].getXVal(), e[i].getYVal());
                    if (e[i].getXVal() < e[i].getFront()) {
                        flag = 1;
                        e[i].updateX((update / 1000));
                    } else if (e[i].getXVal() >= e[i].getFront()) {
                        flag = 2;
                        e[i].updateX((update / 1000));
                    }
                }
            } else if(i % 3 == 1) {
                if ((e[i].getXBox() == xBox) && (e[i].getYBox() == yBox)) {
                    e[i].getAnim().draw(e[i].getXVal(), e[i].getYVal());
                    if (e[i].getYVal() < e[i].getUpAndDown()) {
                        flag = 1;
                        e[i].updateY((update / 1000));
                    } else if (e[i].getYVal() >= e[i].getUpAndDown()) {
                        flag = 2;
                        e[i].updateY((update / 1000));
                    }
                }
            }else if(i % 3 == 2){
                if ((e[i].getXBox() == xBox) && (e[i].getYBox() == yBox)) {
                    e[i].getAnim().draw(e[i].getXVal(), e[i].getYVal());
                    if (e[i].getXVal() < e[i].getFront() && e[i].getYVal() < e[i].getUpAndDown()) {
                        flag = 1;
                        e[i].updateXandY((update / 1000), (update / 1000));
                    } else {
                        flag = 2;
                        e[i].updateXandY((update / 1000), (update / 1000));
                    }
                }
            }

        }
    }

    /* update graphics*/
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);

        if (flag == 1) {
            update += i;
        } else if (flag == 2) {
            update -= i;
        }
        /* move to new level if goal is accomplished*/
        if (orangeGoal <= super.score) {
            state = 2;
            super.addScore(super.score);
            sbg.enterState(splashScreen, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));
        }

        /*Game over if lives run out*/
        if (lives <= 0) {
            super.addScore(super.score);
            sbg.enterState(gameOver);
        }

        if (isIntersect()) {
            lives = decrementLife(lives, i);
            delay(i);
        }

    }

    /**
     * if the player was in contact with enemy, decrease his life by one
     * @param life
     * @param delta
     * @return life
     */
    public double decrementLife(double life, int delta) {
        double ms = 1000.0;
        while (ms > 0) {
            ms -= delta * .5f;
            life -= delta * .5f;
        }

        System.out.printf("%f\t%f\t\n", ms, life);
        return life;
    }

    /**
     * delays game for one second
     * @param delta 
     */
    public void delay(int delta) {
        double ms = 1000.0;
        while (ms > 0) {
            ms -= delta * .1f;
            System.out.println(ms);
        }
    }

    /**
     * returns true if the player was in contact with enemy, false otherwise
     * @return 
     */
    public boolean isIntersect() {
        for (int j = 0; j < numEnemies; j++) {
            if (player.intersects(e[j].getRectangle())) {
                if ((e[j].getXBox() == xBox) && (e[j].getYBox() == yBox)) {
                    player.setX(10*1.0f);
                    player.setY(10*1.0f);
                    super.x = 0;
                    super.y = 0;
                    return true;
                }
            }
        }
        return false;
    }

    /* change location of sprite based on key presses*/
    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }

}
