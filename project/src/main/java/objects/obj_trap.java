package objects;

import entities.InanimateEntity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;

public class obj_trap extends InanimateEntity
{
    public obj_trap(int setX, int setY)
    {
        super(setX, setY);
        this.config_hitbox();
        set_name("Trap");
        set_canCollide(false);

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/traps/oil_trap.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Overide default configure hitbox (called on constructor ONLY)
    private void config_hitbox()
    {
        Rectangle config = new Rectangle();
        config.x = 3;           // hitbox border width
        config.y = 3;           // hitbox border height
        config.width = 42;      // hitbox width
        config.height = 20;     // hitbox height
        this.set_hitbox(config);
    }
}
