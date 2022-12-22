/* 
 *  EnemyTest.java
 * 
 *  Description: Tests methods of Enemy class.
 * 
 *  Last changed: Nov 20th, 2022
 *
*/
 
package org.example;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import moving_objects.Enemy;
import entities.Coordinate;
 
public class EnemyTest extends TestCase
{
    // Attributes
    private Enemy enemy;
    private Simulator sim;
 
    /**
     * Create the test case.
     * @param testName name of the test case
    */
    public EnemyTest(String testName)
    {
        super(testName);
        sim = new Simulator();
        KeyBoard key = new KeyBoard(sim);
        this.enemy = new Enemy(sim, new CheckCollision(sim, key, new EntityList()), 0, 0);
    }
 
    /**
    * @return the suite of tests being tested
    */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(EnemyTest.class);
        suite.addTest(new EnemyTest("testDirectionGeneration"));
        suite.addTest(new EnemyTest("onPlayerCollisionLives"));
        suite.addTest(new EnemyTest("onPlayerCollisionPosition"));
        suite.addTest(new EnemyTest("hitboxExists"));
        suite.addTest(new EnemyTest("moveUp"));
        suite.addTest(new EnemyTest("moveDown"));
        suite.addTest(new EnemyTest("moveRight"));
        suite.addTest(new EnemyTest("moveLeft"));
        suite.addTest(new EnemyTest("moveWhenCollision"));
        return suite;
    }
 
    /**
     * @Test if enemy generated a new direction successfully
    */
    public void testDirectionGeneration()
    {
        enemy.set_actionInterval(74);
        enemy.nextMove();
        assertEquals(0, enemy.get_actionInterval());
    }

    /**
     * @Test if enemy collsion deducts player lives
     */
    public void onPlayerCollisionLives()
    {
        int lives = sim.get_player().get_lives();
        enemy.player_onCollision();
        assertEquals(lives-1, sim.get_player().get_lives());
    }

    /**
     * @Test if enemy collsion resets player position
     */
    public void onPlayerCollisionPosition()
    {
        Coordinate position = sim.get_player().get_coordinate();
        enemy.player_onCollision();
        assertTrue(position.equals(sim.get_player().get_coordinate()));
    }

    /**
     * @Test check if hitbox of enemy is not null on initialization
     */
    public void hitboxExists()
    {
        assertTrue(enemy.get_hitbox() != null);
    }

    /**
     * @Test moving enemy position up by movespeed
     */
    public void moveUp()
    {
        enemy.set_coordinate(0, 0);
        enemy.set_direction("up");
        enemy.moveEntity(false, null);
        assertEquals(enemy.get_moveSpeed()*-1, enemy.get_coordinate_Y());
    }

    /**
     * @Test moving enemy position down by movespeed
     */
    public void moveDown()
    {
        enemy.set_coordinate(0, 0);
        enemy.set_direction("down");
        enemy.moveEntity(false, null);
        assertEquals(enemy.get_moveSpeed(), enemy.get_coordinate_Y());
    }

    /**
     * @Test moving enemy position right by movespeed
     */
    public void moveRight()
    {
        enemy.set_coordinate(0, 0);
        enemy.set_direction("right");
        enemy.moveEntity(false, null);
        assertEquals(enemy.get_moveSpeed(), enemy.get_coordinate_X());
    }

    /**
     * @Test moving enemy position left by movespeed
     */
    public void moveLeft()
    {
        enemy.set_coordinate(0, 0);
        enemy.set_direction("left");
        enemy.moveEntity(false, null);
        assertEquals(enemy.get_moveSpeed()*-1, enemy.get_coordinate_X());
    }

    /**
     * @Test enemy movement when canCollide is true (colliding with wall)
    */
    public void moveWhenCollision()
    {
        enemy.set_coordinate(0, 0);
        enemy.set_direction("left");
        enemy.set_canCollide(true);
        enemy.update();
        assertEquals(0, enemy.get_coordinate_Y());
    }
}
