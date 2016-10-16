package orangepickinggame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author JasmeetKaur
 */
public class Play extends BasicGameState {

    private TiledMap map;
    private Animation sprite, up, down, left, right;
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
        Image[] moveUp = {new Image("spriteUP.png"), new Image("spriteUP.png")};
        Image[] moveDown = {new Image("spriteDOWN.png"), new Image("spriteDOWN.png")};
        Image[] moveLeft = {new Image("spriteLEFT.png"), new Image("spriteLEFT.png")};
        Image[] moveRight = {new Image("spriteRIGHT.png"), new Image("spriteRIGHT.png")};
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
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
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