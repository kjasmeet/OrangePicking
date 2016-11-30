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
    int lives;
    int numEnemies = 1;
    
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
        super.x = 0; super.y = 0;
        super.xHeight = 0; super.yHeight = 0;
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
        int[] duration = {300,300};
        enemy = new Animation(en, duration, false);
        
        int k = 200;
        int bx = 0;
        int by = 0;
        
        for(int i = 0; i<numEnemies;i++){
            if(k>=600){
                k-=600;
                bx++;
            }
            e[i] = new Enemy(20,k,bx,by,i,enemy);
            k+=200;
        }
    }

    /* render graphics for level*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(420, 80, "Lives left: " + lives/1000, Color.yellow);
        font.drawString(50, 20, "Level 2 - Avoid the enemies!", Color.yellow);
        
        for(int i=0;i<numEnemies;i++){
            if((e[i].getXBox()==xBox)&&(e[i].getYBox()==yBox)){
                e[i].getAnim().draw(e[i].getXVal(),e[i].getYVal());
            }
        }
    }

    /* update graphics*/
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        /* move to new level if goal is accomplished*/
        if (orangeGoal <= super.score) {
            state = 2;
            sbg.enterState(splashScreen, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));
        }
        
        /*Game over if lives run out*/ 
        if(lives <= 0){
            sbg.enterState(gameOver);
        }
        
        for(int j = 0; j<numEnemies; j++){
            /*e[i].updateRectangle(i, 0);*/
            if(player.intersects(e[j].getRectangle())){
                if((e[j].getXBox()==xBox)&&(e[j].getYBox()==yBox)){
                    lives-=i*3;       
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
