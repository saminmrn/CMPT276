package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import objects.*;

public class AssetCreatorTest extends TestCase {
    //Attributes
    private Simulator sim;
    private AssetCreator assets;

    /**
     * Create the test case.
     * @param testName name of the test case
     */
    public AssetCreatorTest(String testName){
        super(testName);
        sim = new Simulator();
        assets = new AssetCreator(sim, new EntityList());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(EntityListTest.class);
        suite.addTest(new AssetCreatorTest("checkIfCoordinatePasses"));
        suite.addTest(new AssetCreatorTest("checkIfTileExists"));
        suite.addTest(new AssetCreatorTest("creatingObjects"));
        suite.addTest(new AssetCreatorTest("creatingEnemies"));
        suite.addTest(new AssetCreatorTest("addingApple"));
        suite.addTest(new AssetCreatorTest("createDoor"));
        return suite;
    }


    /**
     * @test checking if coordinate isn't occupied by other objects
     */
    public void checkIfCoordinatePasses(){
        assets.getEntityList().clear_objList();
        int tempX = 5, tempY = 10;
        obj_trap Trap = new obj_trap(tempX, tempY);
        assets.getEntityList().add_obj(Trap);
        boolean spaceIsOkay = assets.checkObjectAtCoordinate(assets.getEntityList().get_obj_at_index(0).get_coordinate_X(),
                assets.getEntityList().get_obj_at_index(0).get_coordinate_Y(),true );
        assertEquals(spaceIsOkay, false);
    }


    /**
     * @test checking if tile exists so object can be placed
     */
    public void checkIfTileExists(){
        assets.getEntityList().clear_objList();
        int tempX = 5, tempY = 10;
        obj_trap Trap = new obj_trap(tempX, tempY);
        assets.getEntityList().add_obj(Trap);
        boolean tileIsReal = assets.checkObjectAtCoordinate(assets.getEntityList().get_obj_at_index(0).get_coordinate_X(),
                assets.getEntityList().get_obj_at_index(0).get_coordinate_Y(), true);
        assertEquals(tileIsReal, false);

    }

    /**
     * @test creates set of objects for game
     */
    public void creatingObjects(){
        assets.getEntityList().clear_objList();
        assets.setObject();
        assertEquals(assets.getEntityList().get_obj_at_index(17).get_name(),"Trap");

    }

    /**
     * @test creates set of enemies
     */
    public void creatingEnemies(){
        assets.getEntityList().clear_enemyList();
        assets.setEnemy(0);
        assertEquals(assets.getEntityList().get_enemy_at_index(3).get_coordinate_X(), 48*6);
    }
    /**
     * @test creates a set of apples for them to reappear on the map
     */
    public void addingApple(){
        assets.getEntityList().clear_objList();
        assets.addApple();
        assertEquals(assets.getEntityList().get_obj_at_index(0).get_name(), "Apple");
    }
    /**
     * @test creates a new door
     */
    public void createDoor(){
        assets.getEntityList().clear_objList();
        assets.addDoor(2);
        assertEquals(assets.getEntityList().get_obj_at_index(0).get_name(), "Door");
    }
}
