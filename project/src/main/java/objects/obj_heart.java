package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import entities.InanimateEntity;

public class obj_heart extends InanimateEntity {
    public obj_heart() {
        set_name("Heart");
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/life/heart.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
