package org.example;

import entities.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import objects.*;


public class SimulatorTest extends TestCase {
    //Attributes
    private Simulator sim;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public SimulatorTest(String testName){
        super(testName);
        sim = new Simulator();
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        TestSuite suite = new TestSuite(SimulatorTest.class);
        suite.addTest(new SimulatorTest("setupGame"));
        suite.addTest(new SimulatorTest("resetStats"));
        suite.addTest(new SimulatorTest("resetObjects"));
        suite.addTest(new SimulatorTest("screenWidth"));
        suite.addTest(new SimulatorTest("screenHeight"));
        suite.addTest(new SimulatorTest("tileSize"));
        suite.addTest(new SimulatorTest("testPlayer"));
        suite.addTest(new SimulatorTest("defaultXCoordinate"));
        suite.addTest(new SimulatorTest("defaultYCoordinate"));
        suite.addTest(new SimulatorTest("testEntityList"));
        suite.addTest(new SimulatorTest("startingGameThread"));
        suite.addTest(new SimulatorTest("updateGame"));
        suite.addTest(new SimulatorTest("testSoundEffect"));
        suite.addTest(new SimulatorTest("resetPlayerPosition"));
        suite.addTest(new SimulatorTest("addPlayerLives"));
        return suite;
    }
    /**
     * @test sets up the game
     */
    public void setupGame(){
        sim.game_setup();
        assertEquals(sim.gameState, 5);
    }
    /**
     * @test resets game stats
     */
    public void resetStats(){
        sim.reset();
        assertEquals(sim.player.get_lives(), 3);
    }
    /**
     * @test resets the objects of the game
     */
    public void resetObjects(){
        sim.restart();
        Coordinate tempX = sim.player.get_coordinate();
        assertEquals(tempX.get_X(), 96);
    }
    /**
     * @test screen width
     */
    public void screenWidth(){
        assertEquals(sim.get_screen_width(), 1296);
    }
    /**
     * @test screen height
     */
    public void screenHeight(){
        assertEquals(sim.get_screen_height(), 720);
    }
    /**
     * @test tile size
     */
    public void tileSize(){
        assertEquals(sim.get_tileSize(), 48);
    }
    /**
     * @test player is created
     */
    public void testPlayer(){
        assertTrue(sim.get_player() != null);
    }
    /**
     * @test player defaulted x location
     */
    public void defaultXCoordinate(){
        assertEquals(sim.get_player_default_x(), 48*2);
    }
    /**
     * @test player defaulted y location
     */
    public void defaultYCoordinate(){
        assertEquals(sim.get_player_default_y(), 48*3);
    }
    /**
     * @test gets the current list of entities
     */
    public void testEntityList(){
        sim.get_entitylist().clear_objList();
        sim.get_entitylist().clear_enemyList();
        assertTrue(sim.get_entitylist() != null);
    }
    /**
     * @test game thread
     */
    public void startingGameThread(){
        Simulator tempSim= sim;
        sim.startGameThread();
        tempSim.startGameThread();
        assertEquals(sim.gameThread, tempSim.gameThread);
    }
    /**
     * @test updates game
     */
    public void updateGame(){
        sim.gameState = sim.playGameState;
        sim.timer_count = 450;
        sim.get_entitylist().clear_enemyList();
        sim.get_entitylist().clear_objList();
        sim.get_entitylist().add_obj(new obj_apple(5, 10));
        sim.update();
        assertEquals(sim.get_entitylist().get_objList_size(), 1);
    }
    /**
     * @test sound effect
     */
    public void testSoundEffect(){
        assertEquals(sim.PlaySoundEffect(0), 0);
    }
    /**
     * @test resets the player position
     */
    public void resetPlayerPosition(){
        sim.reset_player_position();
        assertEquals(sim.player.get_coordinate_X(), 2*48);
    }
    /**
     * @test adding the player lives
     */
    public void addPlayerLives(){
        int tempLives = 1;
        sim.add_player_lives(tempLives);
        assertEquals(sim.player.get_lives(), 4);
    }



}
