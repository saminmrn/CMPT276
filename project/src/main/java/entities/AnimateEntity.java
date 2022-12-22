/*  
 *  AnimateEntity.java
 *  
 *  Description: Abstract class, inherites Entity and has movespeed.
 * 
 *  Author: Lionel (msg me if u have any questions about this class)
 * 
 *  Last changed: Nov 1st, 2022
 *
*/

package entities;
import java.awt.image.BufferedImage;
import main.Simulator;
import main.CheckCollision;
import main.KeyBoard;

public abstract class AnimateEntity extends Entity
{
    // Default attributes
    private int spriteCnt = 0;
    private int spriteNum = 1;
    private int moveSpeed = 3;

    // Constructed attributes
    protected Simulator sim;
    protected CheckCollision collideCheck;
    public BufferedImage down1, down2, down3, up1, up2, up3, right1, right2, left1, left2;
    private String direction;

    // Default constructor (must pass X/Y coordinates and sets moveSpeed to default)
    public AnimateEntity(int setX, int setY, Simulator setSim, CheckCollision setCol)
    {
        super(setX, setY);
        this.sim = setSim;
        this.collideCheck = setCol;
        this.direction = "down";
    }

    // Getters
    public int get_moveSpeed() { return this.moveSpeed; }
    public String get_direction() { return this.direction; }
    public int get_spriteNum() { return this.spriteNum; }

    // Setters
    public void set_moveSpeed(int val) { this.moveSpeed = val; }
    public void set_direction(String val) { this.direction = val; }


    // Default update, updates AnimateEntity movement
    public void update()
    {
        nextMove();
        this.set_canCollide(false);
        this.collideCheck.checkTile(this);
        this.collideCheck.checkPlayer(this);
        this.collideCheck.checkEnemy(this);

        if(this.get_canCollide() == false)
        {
            moveEntity(false, null);
        }
        // Animation change
        this.increase_spriteCnt();
    }

    // Default move entity
    public void moveEntity(boolean isPlayer, KeyBoard keyboard)
    {
        if(isPlayer == false)
        {
            if (this.get_direction() == "right") this.set_coordinate_X(this.get_coordinate_X() + this.get_moveSpeed());
            if (this.get_direction() == "left") this.set_coordinate_X(this.get_coordinate_X() - this.get_moveSpeed());
            if (this.get_direction() == "up") this.set_coordinate_Y(this.get_coordinate_Y() - this.get_moveSpeed());
            if (this.get_direction() == "down") this.set_coordinate_Y(this.get_coordinate_Y() + this.get_moveSpeed());
            return;
        }
        if(keyboard.PressedRT == true) this.set_coordinate_X(this.get_coordinate_X() + this.get_moveSpeed());
        if(keyboard.PressedLF == true) this.set_coordinate_X(this.get_coordinate_X() - this.get_moveSpeed());
        if(keyboard.PressedUp == true) this.set_coordinate_Y(this.get_coordinate_Y() - this.get_moveSpeed());
        if(keyboard.PressedDown == true) this.set_coordinate_Y(this.get_coordinate_Y() + this.get_moveSpeed());
    }

    // TO BE OVERRIDDEN: Set the direction to move in next time update is called
    public void nextMove() {}

    // Increase sprite counter, used for sprite animation
    public void increase_spriteCnt() 
    { 
        this.spriteCnt++;

        if(spriteCnt > 18)  // Increase/decrease this num to change animation frequency
        {
            if(spriteNum < 4)
                this.spriteNum++;
            else
                this.spriteNum = 1;
            this.spriteCnt = 0;
        }
    }
}
