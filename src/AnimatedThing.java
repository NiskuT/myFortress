import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.Duration;

public abstract class AnimatedThing {

    protected Double x;
    protected Double vx;
    protected Double ax;
    protected Double ay;
    protected Double vy;
    protected Double y;

    protected ImageView sprite;

    protected Integer attitude;
    protected String fileName;
    protected Integer sens;


    protected Integer index;
    protected Integer indexMax;
    protected long lastCall;

    protected Integer winHeight = 400;
    protected Integer winWidth = 600;

    protected boolean doubleJump = false;

    public AnimatedThing(Double x, Double y,String fileName, Integer attitude) {


        this.x = x;
        this.y = y;


        this.fileName=fileName;
        this.sprite = new ImageView(new Image(fileName));

        this.attitude = attitude;
        index = 0;
    }

    public ImageView getSprite() {
        return sprite;
    }

    abstract public void update(Double xCamera, long time);

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }


}
