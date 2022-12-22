package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import moving_objects.Player;

public class SoundTest extends TestCase {
    sound Sound= new sound();
    Simulator sim = new Simulator();
    KeyBoard key = new KeyBoard(sim);
    Player player= new Player(sim, key, new CheckCollision(sim, key, new EntityList()), 0, 0);
    public SoundTest(String testName)
    {
        super(testName);
    }
    /**
     * @Test object collision with banana sound effect
     */
    public void testbananaCollisionSound()
    {
        player.object_collision("Banana");
        assertEquals(sim.PlaySoundEffect(2), Sound.getSoundIndex("Banana"));
    }
    /**
     * @Test object collision with apple sound effect
     */
    public void appleCollisionSoundTest()
    {
        player.object_collision("Apple");
        assertEquals(sim.PlaySoundEffect(1), Sound.getSoundIndex("Apple"));
    }
    /**
     * @Test object collision with trap sound effect
     */
    public void trapCollisionSoundTest()
    {
        player.object_collision("Trap");
        assertEquals(sim.PlaySoundEffect(3), Sound.getSoundIndex("Trap"));
    }
    /**
     * @Test object collision with enemy sound effect
     */
    public void enemyCollisionSoundTest()
    {
        player.object_collision("Enemy");
        assertEquals(sim.PlaySoundEffect(3), Sound.getSoundIndex("Enemy"));
    }
    /**
     * @Test object collision with door sound effect
     */
    public void DoorCollisionSoundTest()
    {
        player.object_collision("Door");
        assertEquals(sim.PlaySoundEffect(0), Sound.getSoundIndex("Door"));
    }
    public static Test suite()
    {
        TestSuite suite = new TestSuite(SoundTest.class);
        suite.addTest(new SoundTest("testbananaCollisionSound"));
        suite.addTest(new SoundTest("appleCollisionSoundTest"));
        suite.addTest(new SoundTest("trapCollisionSoundTest"));
        suite.addTest(new SoundTest("enemyCollisionSoundTest"));
        suite.addTest(new SoundTest("DoorCollisionSoundTest"));
        return suite;

    }

}
