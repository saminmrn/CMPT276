package main;
import moving_objects.Enemy;
import objects.*;

import java.util.Random;

public class AssetCreator 
{
    private Simulator sim;
    private EntityList entityList;
    private int mapBoundaryX;
    private int mapBoundaryY;
    private int tileSize;

    // Default constructor
    public AssetCreator(Simulator sim, EntityList eList){
        this.sim = sim;
        this.entityList = eList;
        this.mapBoundaryX = sim.maxScreenCol-2;
        this.mapBoundaryY = sim.maxScreenRow-2;
        this.tileSize = sim.get_tileSize();
    }

    // Getter
    public EntityList getEntityList() { return this.entityList; }

    // Helper function: Checks if an object exists at specified coordinate
    public boolean checkObjectAtCoordinate(int x, int y, boolean isTrap)
    {
        for(int j=0; j<entityList.get_objList_size(); j++)
        {
            int tmpX = entityList.get_obj_at_index(j).get_coordinate_X()/tileSize;
            int tmpY = entityList.get_obj_at_index(j).get_coordinate_Y()/tileSize;
            if(tmpX == x && tmpY == y)
                return true;
            if(tmpX == 2 && tmpY == 3)
                return true;

            // Prevent traps from spawning in adjacent tiles to other traps
            if(isTrap == true)
            {
                if(tmpX+1 == x && tmpY == y)
                    return true;
                if(tmpX-1 == x && tmpY == y)
                    return true;
                if(tmpX == x && tmpY+1 == y)
                    return true;
                if(tmpX == x && tmpY-1 == y)
                    return true;
                if(tmpX+1 == x && tmpY+1 == y)
                    return true;
                if(tmpX-1 == x && tmpY+1 == y)
                    return true;
                if(tmpX-1 == x && tmpY-1 == y)
                    return true;
                if(tmpX+1 == x && tmpY-1 == y)
                    return true;
            }
        }
        return false;
    }

    // Helper function: Checks if a collidable tile exists at specified coordinate
    private boolean checkTileAtCoordinate(int x, int y, boolean isTrap)
    {
        if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[sim.currentMap][x][y]].collision == true)
            return true;

        // Checks if a bridge is nearby and (for traps only) if near the exit door
        if(isTrap == true)
        {
            //  (*NEEDS CLEAN UP, HARD-CODED VALUES*)
            // These values represent the floor in the room that has the door
            if (((x >= 3 && x <= 7) && (y >=9 && y <= 11)))
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[sim.currentMap][x+1][y]].isBridge == true)
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[sim.currentMap][x-1][y]].isBridge == true)
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[sim.currentMap][x][y+1]].isBridge == true)
                return true;
            if(sim.Tile_c.tile[sim.Tile_c.mapTileNum[sim.currentMap][x][y-1]].isBridge == true)
                return true;
        }

        return false;
    }

    // Create and set objects
    public void setObject() 
    {
        int mapNum = sim.Tile_c.get_currentMap();

        // place door at fixed location (1 per map)
        addDoor(mapNum);

        // placing bananas randomly (8 bananas per map)
        AddBanana();

        // placing apples randomly (2 apples per map)
        addApple();
        
        //placing traps randomly (7 traps per map)
        AddTraps();

    }

    public void addDoor(int mapNum)
    {
        switch(mapNum)
        {
            case 0:
                this.entityList.add_obj(new obj_door(tileSize*3, tileSize*10));
                break;
            case 1:
                this.entityList.add_obj(new obj_door(tileSize*25, tileSize*4));
                break;
            case 2:
                this.entityList.add_obj(new obj_door(tileSize*18, tileSize*10));
                break;
        }
    }

    public void addApple()
    {
        for (int i=0; i<2; i++)
        {
            Random random = new Random();
            int x,y;

            // Generate a coordinate without a collidable tile
            do {
                x = random.nextInt(mapBoundaryX)+1;
                y = random.nextInt(mapBoundaryY)+1;
            }while(checkTileAtCoordinate(x, y, false) == true || checkObjectAtCoordinate(x, y, false) == true);
            this.entityList.add_obj(new obj_apple(tileSize*x, tileSize*y));
        }
    }

    public void AddBanana()
    {
        // placing bananas randomly (8 bananas per map)
        for (int i=0; i<8; i++)
        {
            Random random = new Random();
            int x, y;

            // Generate a coordinate without a collidable tile
            do {
                x = random.nextInt(mapBoundaryX)+1;
                y = random.nextInt(mapBoundaryY)+1;
            } while(checkTileAtCoordinate(x, y, false) == true || checkObjectAtCoordinate(x, y, false) == true);
            this.entityList.add_obj(new obj_banana(tileSize*x, tileSize*y));
        }

    }

    public void AddTraps()
    {
        for (int i=0; i<7; i++)
        {
            Random random = new Random();
            int x, y;

            // Generate a coordinate without a collidable tile
            do {
                x = random.nextInt(mapBoundaryX)+1;
                y = random.nextInt(mapBoundaryY)+1;
            } while(checkTileAtCoordinate(x, y, true) == true || checkObjectAtCoordinate(x, y, true) == true);
            this.entityList.add_obj(new obj_trap(tileSize*x, tileSize*y));
        }

    }
    // Create and set enemy entities, returns true if successful
    public boolean setEnemy(int mapNum) 
    {
        if(mapNum == 0)
        {
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*15, tileSize*4));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*23, tileSize*6));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*12, tileSize*10));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*6, tileSize*10));
            return true;
        }
        if(mapNum == 1)
        {
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*4, tileSize*11));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*9, tileSize*8));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*15, tileSize*4));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*16, tileSize*13));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*23, tileSize*5));
            return true;
        }
        if(mapNum == 2)
        {
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*2, tileSize*10));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*13, tileSize*10));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*9, tileSize*1));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*19, tileSize*1));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*23, tileSize*2));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*7, tileSize*6));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*23, tileSize*7));
            this.entityList.add_enemy(new Enemy(this.sim, tileSize*19, tileSize*10));
            return true;
        }
        return false;
    }
}
