package orangepickinggame;

import java.util.ArrayList;
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
    int numEnemies = 300;
    int update = 0;
    
    int index = 0;
    Point[] point = new Point[10000];
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
    public void enter(GameContainer gc, StateBasedGame sbg) {
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

        int k = 200;
        int bx = 0;
        int by = 0;

        for (int i = 0; i < numEnemies; i++) {
            if (k >= 600) {
                k -= 600;
                bx++;

            }
            e[i] = new Enemy(200, k, bx, by, i, enemy);
            k += 200;
        }
    }

    /* render graphics*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(50, 20, "Level 3 - Watch where you step!" + getHighscore(), Color.yellow);
        
//        for(Rectangle rect: rects){
//            grphcs.setColor(Color.orange);
//            grphcs.fill(rect);
//            if(super.player.intersects(rect)){
//                
//            }
//        }
        for(int i = 0; i < rects.size(); i++){
            grphcs.setColor(Color.orange);
            if(point[i].pointX == xBox && point[i].pointY == yBox){
                grphcs.fill(rects.get(i));
            }
            
        }
        
        for (int i = 0; i < numEnemies; i++) {
            if ((e[i].getXBox() == xBox) && (e[i].getYBox() == yBox)) {
                e[i].getAnim().draw(e[i].getXVal(), e[i].getYVal());
                //e[i].updateRectangle((update / 1000), e[i].getYVal());
            }
        }
    }

    /* update graphics*/
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        if(super.x >= 0 && super.y >= 0){
            
            point[index] = new Point(index, super.xBox, super.yBox);
            rects.add(index, new Rectangle(super.x, super.y, 20, 20));
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
            // e[i].updateRectangle(update, j);
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
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
}
