/*  
 *  PlayerTest.java
 *  
 *  Description: Tests methods of Player class.
 * 
 *  Last changed: Nov 15th, 2022
 *
*/

package org.example;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import moving_objects.Player;
import entities.Coordinate;
import java.awt.Rectangle;
public class PlayerTest extends TestCase
{
    // Attributes
    private Player player;
    private KeyBoard key;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public PlayerTest(String testName)
    {
        super(testName);
        Simulator sim = new Simulator();
        this.key = new KeyBoard(sim);
        this.player = new Player(sim, key, new CheckCollision(sim, key, new EntityList()), 0, 0);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(PlayerTest.class);
        suite.addTest(new PlayerTest("moveUp"));
        suite.addTest(new PlayerTest("moveDown"));
        suite.addTest(new PlayerTest("moveRight"));
        suite.addTest(new PlayerTest("moveLeft"));
        suite.addTest(new PlayerTest("moveWhenCollision"));
        suite.addTest(new PlayerTest("testResetPosition"));
        suite.addTest(new PlayerTest("bananaCollision"));
        suite.addTest(new PlayerTest("appleCollision"));
        suite.addTest(new PlayerTest("trapCollision"));
        suite.addTest(new PlayerTest("invalidCollision"));
        suite.addTest(new PlayerTest("enemyCollision"));
        suite.addTest(new PlayerTest("nullEnemyCollision"));
        suite.addTest(new PlayerTest("checkNoLives"));
        suite.addTest(new PlayerTest("checkHasLives"));
        suite.addTest(new PlayerTest("setHitbox"));
        return suite;
    }

    /**
     * @Test moving character position up by movespeed
    */
    public void moveUp()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedUp = true;
        player.moveEntity(true, key);
        player.get_keyboard().PressedUp = false;
        assertEquals(player.get_moveSpeed()*-1, player.get_coordinate_Y());
    }

    /**
     * @Test moving character position down by movespeed
    */
    public void moveDown()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedDown = true;
        player.moveEntity(true, key);
        player.get_keyboard().PressedDown = false;
        assertEquals(player.get_moveSpeed(), player.get_coordinate_Y());
    }

    /**
     * @Test moving character position right by movespeed
    */
    public void moveRight()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedRT = true;
        player.moveEntity(true, key);
        player.get_keyboard().PressedRT = false;
        assertEquals(player.get_moveSpeed(), player.get_coordinate_X());
    }

    /**
     * @Test moving character position right by movespeed
    */
    public void moveLeft()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedLF = true;
        player.moveEntity(true, key);
        player.get_keyboard().PressedLF = false;
        assertEquals(player.get_moveSpeed()*-1, player.get_coordinate_X());
    }

    /**
     * @Test movement when canCollide is true (colliding with wall)
    */
    public void moveWhenCollision()
    {
        player.set_coordinate(0, 0);
        player.get_keyboard().PressedUp = true;
        player.set_canCollide(true);
        player.update();
        player.get_keyboard().PressedUp = false;
        assertEquals(0, player.get_coordinate_Y());
    }

    /**
     * @Test player reset to start position
    */
    public void testResetPosition()
    {
        player.setDefaultPosition();
        Simulator tmp = new Simulator();
        assertTrue(player.get_coordinate().equals(new Coordinate(tmp.get_player_default_x(), tmp.get_player_default_y())));
    }

    /**
     * @Test object collision with banana
    */
    public void bananaCollision()
    {
        int score = player.get_score();
        player.object_collision("Banana");
        assertEquals(score+20, player.get_score());
    }

    /**
     * @Test object collision with apple
    */
    public void appleCollision()
    {
        int lives = player.get_lives();
        player.object_collision("Apple");
        assertEquals(lives+1, player.get_lives());
    }

    /**
     * @Test object collision with trap
    */
    public void trapCollision()
    {
        int lives = player.get_lives();
        player.object_collision("Trap");
        assertEquals(lives-1, player.get_lives());
    }

    /**
     * @Test collision with invalid object
    */
    public void invalidCollision()
    {
        int lives = player.get_lives();
        int score = player.get_score();
        player.object_collision("");
        assertEquals(lives, player.get_lives());
        assertEquals(score, player.get_score());
    }

    /**
     * @Test collision with enemy resets player position and deducts a life
    */
    public void enemyCollision()
    {
        int lives = player.get_lives();
        Simulator tmp = new Simulator();
        player.set_coordinate(0, 0);
        player.enemy_onCollision(0);
        assertEquals(lives-1, player.get_lives());
        assertTrue(player.get_coordinate().equals(new Coordinate(tmp.get_player_default_x(), tmp.get_player_default_y())));
    }

    /**
     * @Test collision with enemy that does not exist
    */
    public void nullEnemyCollision()
    {
        int lives = player.get_lives();
        player.set_coordinate(0, 0);
        player.enemy_onCollision(-1);
        assertEquals(lives, player.get_lives());
        assertTrue(player.get_coordinate().equals(new Coordinate(0, 0)));
    }

    /**
     * @Test if player has no lives through method in Player
    */
    public void checkNoLives()
    {
        player.set_lives(0);
        assertTrue(player.check_if_no_lives());
    }

    /**
     * @Test if player has no lives through method in Player
    */
    public void checkHasLives()
    {
        player.set_lives(1);
        assertFalse(player.check_if_no_lives());
    }

    /**
     * @Test check if hitbox of player is not null on initialization
     */
    public void hitboxExists()
    {
        assertTrue(player.get_hitbox() != null);
    }

    /**
     * @Test changing player hitbox to new value
     */
    public void setHitbox()
    {
        Rectangle testHitbox = new Rectangle(10, 10, 20, 20);
        player.set_hitbox(testHitbox);
        assertEquals(testHitbox, player.get_hitbox());
    }
}
