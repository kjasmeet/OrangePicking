package orangepickinggame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import static orangepickinggame.OrangePickingGame.gameOver;

/**
 *
 * @author JasmeetKaur
 */
public class Play extends BasicGameState {

    private TiledMap map;
    private Animation sprite, up, down, left, right;
    double time = 120000;
    int x = 20, y = 20;
    int xHeight = 0;
    int yHeight = 0;

    public Play(int playState) {

    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new TiledMap("slick/testdata/map1.tmx");
        Image[] moveUp = {new Image("Images/spriteUP.png"), new Image("Images/spriteUP.png")};
        Image[] moveDown = {new Image("Images/spriteDOWN.png"), new Image("Images/spriteDOWN.png")};
        Image[] moveLeft = {new Image("Images/spriteLEFT.png"), new Image("Images/spriteLEFT.png")};
        Image[] moveRight = {new Image("Images/spriteRIGHT.png"), new Image("Images/spriteRIGHT.png")};
        int[] duration = {300, 300};
        up = new Animation(moveUp, duration, false);
        down = new Animation(moveDown, duration, false);
        left = new Animation(moveLeft, duration, false);
        right = new Animation(moveRight, duration, false);
        sprite = right;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.render(0, 0, yHeight, xHeight, 20, 20);
        sprite.draw(x, y);
        double calculateTime = time / 1000;
        if (calculateTime >= 10) {
            grphcs.setColor(Color.yellow);
        }else{
            grphcs.setColor(Color.red);
        }
        grphcs.drawString("Time left: " + time / 1000, 450, 20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        time -= i;
        if (time <= 0.0) {
            sbg.enterState(gameOver);
        }
    }

    @Override
    public void keyPressed(int key, char c) {

        switch (key) {
            case Input.KEY_RIGHT:
                x += 10;

                if (yHeight < 32 && x >= 630) {
                    x = 10;
                    yHeight += 4;
                }
                break;
            case Input.KEY_LEFT:
                if (x >= 20) {
                    x -= 10;
                }
                if (yHeight >= 4 && x <= 10) {
                    x = 630;
                    yHeight -= 4;
                }

                break;
            case Input.KEY_DOWN:
                y += 10;
                if (xHeight < 32 && y > 600) {
                    y = 20;
                    xHeight += 4;
                }
                break;
            case Input.KEY_UP:
                if (y >= 20) {
                    y -= 10;
                }
                if (xHeight >= 4 && y <= 10) {
                    y = 610;
                    xHeight -= 4;
                }
                break;
            default:
                break;
        }
    }

}
