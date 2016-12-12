package orangepickinggame;

import java.util.ArrayList;
import static orangepickinggame.InitializeCode.RIGHT;
import static orangepickinggame.OrangePickingGame.congrats;
import static orangepickinggame.OrangePickingGame.gameOver;
import static orangepickinggame.OrangePickingGame.playLevelThree;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author JasmeetKaur
 */

/* Level 3 - will eventually contain a version of initial level in which the player cannot retrace steps
 Increased difficulty in orange collection goal and time limit
 */
public class PlayLevelThree extends InitializeCode {
    /* methods are defined, for the most part, in InitializeCode. This extends.*/

    int orangeGoal = 10;

    ArrayList<Rectangle> rects = new ArrayList<>();
    int xrec = 0, yrec = 0;

    double lives;
    int numEnemies = 200;
    int update = 0;
    int flag = 0;
    int index = 0;
    int xprev = 0;
    int yprev = 0;
    int x2prev = 0;
    int y2prev = 0;
    int x3prev = 0;
    int y3prev = 0;
    ArrayList<Point> point = new ArrayList<>();
    private Animation enemy;

    Enemy e[] = new Enemy[numEnemies];

    /* constructor for level*/
    public PlayLevelThree(int playState) {
        super();
    }

    @Override
    public int getID() {
        return playLevelThree;

    }

    /* enter level, sets time limit*/
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.setTime(30000);
        super.score = 0;
        super.x = 0;
        super.y = 0;
        super.xHeight = 0;
        super.yHeight = 0;
        lives = 3000;
    }

    /* initialize level*/
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

    /* render graphics*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        
        font.drawString(420, 80, "Lives left: " + lives / 1000, Color.yellow);
        font.drawString(50, 20, "Level 3 - Watch where you step!" + getHighscore(), Color.yellow);

        for (int i = 0; i < rects.size(); i++) {
            grphcs.setColor(Color.black);
            if (point.get(i).pointX == xBox && point.get(i).pointY == yBox) {
                grphcs.fill(rects.get(i));
            }

        }

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
            } else if (i % 3 == 1) {
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
            } else if (i % 3 == 2) {
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

        if (orangeGoal <= super.score) {
            super.addScore(super.score);
            sbg.enterState(congrats, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

        }

        if (lives <= 0) {
            super.addScore(super.score);
            sbg.enterState(gameOver);
        }
        
        //checks if the player came in contact with enemy
        if (isIntersect()) {
            lives = decrementLife(lives, i);
            delay(i);
        }
    }

    /* change location of sprite based on key presses*/
    @Override
    public void keyPressed(int key, char c) {
        boolean isBlocked = false;

        Rectangle newMove;
            
        switch(key){
            case Input.KEY_LEFT:
                newMove = new Rectangle(x-20,y,20,20);
                if (x3prev >= 0 && y3prev >= 0) {
                    Rectangle newBox = new Rectangle(x3prev, y3prev, 15, 15);
                    point.add(index, new Point(index, super.xBox, super.yBox));
                    rects.add(index, newBox);
                    index++;
                }
                x3prev = x2prev;
                y3prev = y2prev;
                x2prev = xprev;
                y2prev = yprev;
                xprev = x;
                yprev = y;
                break;
            case Input.KEY_RIGHT:
                newMove = new Rectangle(x+20,y,20,20);
                if (x3prev >= 0 && y3prev >= 0) {
                    Rectangle newBox = new Rectangle(x3prev, y3prev, 15, 15);
                    point.add(index, new Point(index, super.xBox, super.yBox));
                    rects.add(index, newBox);
                    index++;
                }
                x3prev = x2prev;
                y3prev = y2prev;
                x2prev = xprev;
                y2prev = yprev;
                xprev = x;
                yprev = y;
                break;
            case Input.KEY_UP:
                newMove = new Rectangle(x,y-20,20,20);
                if (x3prev >= 0 && y3prev >= 0) {
                    Rectangle newBox = new Rectangle(x3prev, y3prev, 15, 15);
                    point.add(index, new Point(index, super.xBox, super.yBox));
                    rects.add(index, newBox);
                    index++;
                }
                x3prev = x2prev;
                y3prev = y2prev;
                x2prev = xprev;
                y2prev = yprev;
                xprev = x;
                yprev = y;
                break;
            case Input.KEY_DOWN:
                newMove = new Rectangle(x,y+20,20,20);
                if (x3prev >= 0 && y3prev >= 0) {
                    Rectangle newBox = new Rectangle(x3prev, y3prev, 15, 15);
                    point.add(index, new Point(index, super.xBox, super.yBox));
                    rects.add(index, newBox);
                    index++;
                }
                x3prev = x2prev;
                y3prev = y2prev;
                x2prev = xprev;
                y2prev = yprev;
                xprev = x;
                yprev = y;
                break;
            default:
                newMove = new Rectangle(-20,-20,20,20);
                break;
                
        }
                            
        for (int d = 0; d < (rects.size()); d++) {
            if (point.get(d).pointX == xBox && point.get(d).pointY == yBox) {
                if (newMove.intersects(rects.get(d))) {
                    isBlocked = true;
                }
            }
        }

     if(isBlocked==false){
         super.keyPressed(key, c);
     }
    }

    /**
     * Checks if the player has been in contact with the enemy
     * @return boolean
     */
    public boolean isIntersect() {
        for (int j = 0; j < numEnemies; j++) {
            if (player.intersects(e[j].getRectangle())) {
                if ((e[j].getXBox() == xBox) && (e[j].getYBox() == yBox)) {
                    player.setX(10 * 1.0f);
                    player.setY(10 * 1.0f);
                    super.x = 0;
                    super.y = 0;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * delays the game for one second
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
     * if the player has been in contact with the enemy, 
     * life is decremented by 1
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
}
