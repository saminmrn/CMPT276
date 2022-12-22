package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import entities.InanimateEntity;

public class obj_banana extends InanimateEntity
{
    public obj_banana(int setX, int setY)
    {
        super(setX, setY);
        set_name("Banana");
        set_canCollide(true);

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/reward/banana.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Dead code that doesn't get used but might be used in the future
    /*
    public obj_banana() {
            set_name("Banana");
            try {
                this.image = ImageIO.read(getClass().getResourceAsStream("/reward/banana.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }

     */
}
