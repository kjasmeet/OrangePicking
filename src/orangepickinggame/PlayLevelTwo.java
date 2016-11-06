package orangepickinggame;

import static orangepickinggame.OrangePickingGame.playLevelThree;
import static orangepickinggame.OrangePickingGame.playLevelTwo;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author JasmeetKaur
 */
public class PlayLevelTwo extends InitializeCode{
    
    int orangeGoal = 10;
    
    public PlayLevelTwo(int playState) {
        super();
        super.setTime(40000);
    }

    @Override
    public int getID() {
        return playLevelTwo;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(50, 20, "Level 2 - Avoid the enemies!", Color.yellow);
        grphcs.setColor(Color.yellow);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        if(super.getTime() <= 0){
            sbg.enterState(playLevelThree);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
}
