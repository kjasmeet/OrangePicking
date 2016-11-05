package orangepickinggame;

import java.util.Random;
import org.newdawn.slick.geom.*;
import java.util.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import static orangepickinggame.OrangePickingGame.gameOver;
import org.lwjgl.input.Mouse;

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
    Image orange;
    Rectangle player = new Rectangle(x,y,25,25);
    int score = 0;
    int numOs = 50;
    
    /*orange collection declarations*/
    Orange o[] = new Orange[numOs];
    boolean isDrawn[] = new boolean[numOs];
    boolean isScored[] = new boolean[numOs];

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
        
        orange = new Image("Images/oranges.png");
        
        Random r = new Random();
        
        for (int i=0;i<numOs;i++){
            int low = 50;
            int high = 600;
            int xl = r.nextInt(high-low) + low;
            int yl = r.nextInt(high-low) + low;
            
            o[i] = new Orange(xl, yl, i);
            isDrawn[i] = true;
            isScored[i] = true;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
        map.render(0, 0, yHeight, xHeight, 20, 20);
        
        sprite.draw(x, y);
        double calculateTime = time / 1000;
        if (calculateTime >= 10) {
            grphcs.setColor(Color.yellow);
        } else {
            grphcs.setColor(Color.red);
        }
        grphcs.drawString("Time left: " + time / 1000, 450, 20);
        grphcs.drawString("Current score: " + score, 450, 40);
        int xx = Mouse.getX();
        int yy = Mouse.getY();
        
        for(int i=0;i<numOs;i++){
            if(isDrawn[i] ==true){
                grphcs.drawImage(orange,o[i].getXVal(),o[i].getYVal());
            }
        }

        grphcs.drawString(xx + " " + yy, 50, 50);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        time -= i;
        if (time <= 0.0) {
            sbg.enterState(gameOver);
        }
        for(int j = 0; j<numOs; j++){
            if(player.intersects(o[j].getRectangle())){
                if(isScored[j]==true){
                    score += 1;
                    isScored[j] = false;
                }
                isDrawn[j] = false;
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {

        switch (key) {
            case Input.KEY_RIGHT:
                sprite = right;
                
                if (yHeight < 80 && x >= 600) {
                     x = 10;
                     yHeight += 20;
                }else if(x < 600){
                    x += 20;
                    player.setX(x);
                }
                break;
            case Input.KEY_LEFT:
                sprite = left;
                if (x >= 20) {
                    x -= 20;
                    player.setX(x);
                }
                if (yHeight >= 4 && x <= 10) {
                     x = 630;
                    yHeight -= 20;
                }

                break;
            case Input.KEY_DOWN:
                sprite = down;
                
                if (y >= 600 && xHeight < 80 ) {
                    y = 20;
                    xHeight += 20;
                }else if(y < 600){
                    y += 20;
                    player.setY(y);
                }
                
                break;
            case Input.KEY_UP:
                sprite = up;
                if (y >= 20) {
                    y -= 20;
                    player.setY(y);
                }
                if (xHeight >= 4 && y <= 10) {
                    y = 610;
                    xHeight -= 20;
                }
                break;
            default:
                break;
        }
    }

}
