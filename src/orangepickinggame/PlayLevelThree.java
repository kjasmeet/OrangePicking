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

    int lives;
    int numEnemies = 100;
    int update = 0;
    int flag = 0;
    int index = 0;
    int xprev = 0;
    int yprev = 0;
    int x2prev = 0;
    int y2prev = 0;
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
        font.drawString(50, 20, "Level 3 - Watch where you step!" + getHighscore(), Color.yellow);

        for (int i = 0; i < rects.size(); i++) {
            grphcs.setColor(Color.orange);
            if (point.get(i).pointX == xBox && point.get(i).pointY == yBox) {
                grphcs.fill(rects.get(i));
            }

        }

        for (int i = 0; i < numEnemies; i++) {
            if (i % 2 == 0) {
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
            } else {
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

        if (super.x >= 0 && super.y >= 0) {
            point.add(index, new Point(index, super.xBox, super.yBox));
            rects.add(index, new Rectangle(x2prev, y2prev, 15, 15));
            index++;

        }

        if (orangeGoal <= super.score) {
            super.addScore(super.score);
            sbg.enterState(congrats, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

        }

        if (lives <= 0) {
            super.addScore(super.score);
            sbg.enterState(gameOver);
        }

        for (int j = 0; j < numEnemies; j++) {
            if (player.intersects(e[j].getRectangle())) {
                if ((e[j].getXBox() == xBox) && (e[j].getYBox() == yBox)) {
                    lives -= i * 5;
                    super.x = 0;
                    super.y = 0;
                }
            }
        }
    }

    /* change location of sprite based on key presses*/
    @Override
    public void keyPressed(int key, char c
    ) {
        x2prev = xprev;
        y2prev = yprev;
        xprev = x;
        yprev = y;
        super.keyPressed(key, c);
        for (int d = 0; d < (rects.size()); d++) {
            if (point.get(d).pointX == xBox && point.get(d).pointY == yBox) {
                if (player.intersects(rects.get(d))) {
                    switch (key) {
                        case Input.KEY_LEFT:
                            if (yHeight < 80 && x >= 590) {
                                x = 10;
                                yHeight += 20;
                                yBox++;
                            } else if (x < 590) {
                                x += 20;
                                player.setX(x);
                            }
                        case Input.KEY_DOWN:
                            if (y >= 20) {
                                y -= 20;
                                player.setY(y);
                            }
                            if (xHeight >= 4 && y <= 10) {
                                y = 610;
                                xHeight -= 20;
                                xBox--;
                            }
                        case Input.KEY_UP:
                            if (y >= 600 && xHeight < 80) {
                                y = 20;
                                xHeight += 20;
                                xBox++;
                            } else if (y < 600) {
                                y += 20;
                                player.setY(y);
                            }
                        case Input.KEY_RIGHT:
                            if (x >= 20) {
                                x -= 20;
                                player.setX(x);
                            }
                            if (yHeight >= 4 && x <= 10) {
                                x = 590;
                                yHeight -= 20;
                                yBox--;
                            }
                    }
                }
            }
        }
    }
}
