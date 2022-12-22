/* 
 *  EntityListTest.java
 * 
 *  Description: Tests methods of EntityList class.
 * 
 *  Last changed: Nov 21st, 2022
 *
*/

package org.example;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;
import moving_objects.Enemy;
import objects.*;

public class EntityListTest extends TestCase
{
    // Attributes
    private EntityList list;

    /**
     * Create the test case.
     * @param testName name of the test case
    */
    public EntityListTest(String testName)
    {
        super(testName);
        list = new EntityList();
    }

    /**
    * @return the suite of tests being tested
    */
    public static Test suite()
    {
        TestSuite suite = new TestSuite(EntityListTest.class);
        suite.addTest(new EntityListTest("addObject"));
        suite.addTest(new EntityListTest("testClearObject"));
        suite.addTest(new EntityListTest("getSizeObject"));
        suite.addTest(new EntityListTest("getObjectAtIndex"));
        suite.addTest(new EntityListTest("deleteObjectAtIndex"));
        suite.addTest(new EntityListTest("addEnemy"));
        suite.addTest(new EntityListTest("testClearEnemy"));
        suite.addTest(new EntityListTest("getSizeEnemy"));
        suite.addTest(new EntityListTest("getEnemyAtIndex"));
        suite.addTest(new EntityListTest("deleteEnemyAtIndex"));
        return suite;
    }

    /**
     * @Test adding object to object list
    */
    public void addObject()
    {
        list.clear_objList();
        obj_apple toAdd = new obj_apple(5, 10);
        list.add_obj(toAdd);
        assertEquals(toAdd, list.get_obj_at_index(0));
    }

    /**
     * @Test clear object list
    */
    public void testClearObject()
    {
        list.add_obj(new obj_apple(5, 10));
        list.clear_objList();
        assertEquals(0, list.get_objList_size());
    }

    /**
     * @Test get object list size
    */
    public void getSizeObject()
    {
        list.clear_objList();
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(new obj_apple(5, 10));
        assertEquals(4, list.get_objList_size());
    }

    /**
     * @Test get object at index
    */
    public void getObjectAtIndex()
    {
        obj_apple toFind = new obj_apple(99, 99);
        list.clear_objList();
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(toFind);
        list.add_obj(new obj_apple(5, 10));
        assertEquals(toFind, list.get_obj_at_index(2));
    }

    /**
     * @Test delete object at index, and all objects should be shift down the list after deletion
    */
    public void deleteObjectAtIndex()
    {
        obj_apple toFind = new obj_apple(99, 99);
        list.clear_objList();
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(new obj_apple(5, 10));
        list.add_obj(toFind);
        list.add_obj(new obj_apple(5, 10));
        list.delete_obj_at_index(1);
        list.delete_obj_at_index(0);
        assertEquals(toFind, list.get_obj_at_index(0));
    }

    /**
     * @Test adding enemy to enemy list
    */
    public void addEnemy()
    {
        list.clear_enemyList();
        Enemy toAdd = new Enemy(null, null, 10, 10);
        list.add_enemy(toAdd);
        assertEquals(toAdd, list.get_enemy_at_index(0));
    }

    /**
     * @Test clear enemy list
    */
    public void testClearEnemy()
    {
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.clear_enemyList();
        assertEquals(0, list.get_enemyList_size());
    }

    /**
     * @Test get enemy list size
    */
    public void getSizeEnemy()
    {
        list.clear_enemyList();
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(new Enemy(null, null, 10, 10));
        assertEquals(4, list.get_enemyList_size());
    }

    /**
     * @Test get enemy at index
    */
    public void getEnemyAtIndex()
    {
        list.clear_enemyList();
        Enemy toFind = new Enemy(null, null, 25, 25);
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(toFind);
        list.add_enemy(new Enemy(null, null, 10, 10));
        assertEquals(toFind, list.get_enemy_at_index(2));
    }

    /**
     * @Test delete enemy at index, and all enemies should be shift down the list after deletion
    */
    public void deleteEnemyAtIndex()
    {
        list.clear_enemyList();
        Enemy toFind = new Enemy(null, null, 25, 25);
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.add_enemy(toFind);
        list.add_enemy(new Enemy(null, null, 10, 10));
        list.delete_enemy_at_index(1);
        list.delete_enemy_at_index(0);
        assertEquals(toFind, list.get_enemy_at_index(0));
    }
}
