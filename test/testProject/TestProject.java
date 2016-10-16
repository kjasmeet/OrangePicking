/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testProject;

import orangepickinggame.Menu;
import static orangepickinggame.OrangePickingGame.menu;
import static orangepickinggame.OrangePickingGame.play;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.newdawn.slick.SlickException;

/**
 *
 * @author JasmeetKaur
 */
public class TestProject {
    Menu menus = new Menu(menu);
    /**
     * testing to see what getID of menu returns
     */
    @Test
    public void testWhatIDReturnsOnMenu() {
        
        assertEquals(0, menus.getID());
    }
    
    /**
     * if the user clicks in the position (100, 410), the next state method
     * should return play state
     * @throws SlickException 
     */
    @Test
    public void testKeyPressedMethod() throws SlickException{
        int xPos = 100;
        int yPos = 410;
        
        assertEquals(play, menus.goToNextState(xPos, yPos));
    } 
}
