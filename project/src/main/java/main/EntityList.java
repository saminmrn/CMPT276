/*  
 *  EntityList.java
 *  
 *  Description: Stores array of existing entity classes.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Oct 30th, 2022
 *
*/

package main;
import entities.*;
import moving_objects.Enemy;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class EntityList 
{
    // List of entity arrays
    private List<Enemy> enemyList;
    private List<InanimateEntity> objList;

    // Default constructor
    public EntityList()
    {
        this.enemyList = new ArrayList<Enemy>();
        this.objList = new ArrayList<InanimateEntity>();
    }

    // EnemyList functions
    public void add_enemy(Enemy toAdd) { this.enemyList.add(toAdd);}
    public List<Enemy> get_enemyList() { return this.enemyList; }
    public int get_enemyList_size() { return this.enemyList.size(); }
    public Enemy get_enemy_at_index(int val) { return this.enemyList.get(val); }
    public void delete_enemy_at_index(int val) { this.enemyList.remove(val); }
    public void clear_enemyList() { this.enemyList.clear(); }

    public void update_enemyList()
    {
        for(int i = 0; i < enemyList.size(); i++)
            this.enemyList.get(i).update();
    }

    public void draw_enemyList(Graphics2D g2)
    {
        for(int i = 0; i < enemyList.size(); i++)
            this.enemyList.get(i).draw(g2);
    }

    // ObjectList functions
    public void add_obj(InanimateEntity toAdd) { this.objList.add(toAdd);}
    public List<InanimateEntity> get_objList() { return this.objList; }
    public int get_objList_size() { return this.objList.size(); }
    public InanimateEntity get_obj_at_index(int val) { return this.objList.get(val); }
    public void delete_obj_at_index(int val) { this.objList.remove(val); }
    public void clear_objList() { this.objList.clear(); }

    public void draw_objList(Graphics2D g2, Simulator sim)
    {
        for(int i = 0; i < objList.size(); i++)
            this.objList.get(i).draw(g2, sim);
    }
}
