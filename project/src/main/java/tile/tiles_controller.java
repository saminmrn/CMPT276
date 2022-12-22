package tile;

import main.Simulator;
import main.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tiles_controller 
{
    public Simulator sim;
    public Tools tools;
    public Tiles[] tile;
    public int mapTileNum[][][];

    public tiles_controller(Simulator sim)
    {
        this.sim=sim;
        tile= new Tiles[35]; //number of different tiles
        mapTileNum = new int[sim.maxMap][sim.maxScreenCol][sim.maxScreenRow];

        get_tile_png();
        mapLoad("/maps/map01.txt",0);
        mapLoad("/maps/map02.txt",1);
        mapLoad("/maps/map03.txt",2);

    }

    public int get_currentMap() { return sim.currentMap; }

    public void get_tile_png()
    {


        setup(0,"floors/floor",false);
        setup(1,"blank",true);
        setup(2,"floors/floor_side_down",false);
        setup(3,"floors/floor_side_up",false);
        setup(4,"floors/floor_side_right",false);
        setup(5,"floors/floor_side_left",false);
        setup(6,"floors/floor_corner_1",false);
        setup(7,"floors/floor_corner_2",false);
        setup(8,"floors/floor_corner_3",false);
        setup(9,"floors/floor_corner_4",false);
        setup(10,"floors/floor_inner_1",false);
        setup(11,"floors/floor_inner_2",false);
        setup(12,"floors/floor_inner_3",false);
        setup(13,"floors/floor_inner_4",false);
        setup(14,"floors/floor_horizontal",false);
        tile[14].isBridge = true;
        setup(15,"floors/floor_vertical",false);
        tile[15].isBridge = true;
        setup(16,"walls/wall",true);
        setup(17,"walls/wall_side_right",true);
        setup(18,"walls/wall_side_left",true);
        setup(19,"walls/wall_side_up",true);
        setup(20,"walls/wall_corner_1",true);
        setup(21,"walls/wall_corner_2",true);
        setup(22,"walls/wall_ledge",true);
        setup(23,"walls/wall_ledge_right",true);
        setup(24,"walls/wall_ledge_left",true);
        setup(25,"walls/wall_door",true);
        setup(26,"blank_alt_1",true);
        setup(27,"blank_alt_2",true);
        setup(28,"borders/border_horizontal",true);
        setup(29,"borders/border_vertical",true);
        setup(30,"borders/border_corner_1",true);
        setup(31,"borders/border_corner_2",true);
        setup(32,"borders/border_corner_3",true);
        setup(33,"borders/border_corner_4",true);
    }

    public void setup(int index, String imageName, boolean collision) {

        Tools uTool = new Tools();
        try{
            tile[index] = new Tiles();
            tile[index].image= ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, sim.get_tileSize(),sim.get_tileSize());
            tile[index].collision = collision;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mapLoad(String file, int map){
        try{
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < sim.maxScreenCol && row < sim.maxScreenRow){
                String line = br.readLine();

                while (col < sim.maxScreenCol){
                    String num[] = line.split(" ");

                    int number = Integer.parseInt(num[col]);

                    mapTileNum[map][col][row] = number;
                    col++;
                }
                if (col == sim.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){

        }
    }

    public void draw (Graphics2D g)
    {
        int currentCol=0;
        int currentRow=0;
        int drawingRow=0;
        int drawingCol=0;
        while (currentCol<sim.maxScreenCol && currentRow < sim.maxScreenRow)
        {
            int tileNum = mapTileNum[sim.currentMap][currentCol][currentRow];
            g.drawImage(tile[tileNum].image,drawingRow,drawingCol, null);

            currentCol++;
            drawingRow+= sim.get_tileSize();
            if (currentCol == sim.maxScreenCol)
            {
                currentCol=0;
                currentRow++;
                drawingRow=0;
                drawingCol+= sim.get_tileSize();

            }
        }
    }
}
