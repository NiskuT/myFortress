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

    protected String fileName;

    protected Integer index;
    protected Integer indexMax;
    protected String state;
    protected String prevState;
    protected long lastCall;

    protected Integer winHeight = 400;
    protected Integer winWidth = 600;

    protected boolean doubleJump = false;

    public AnimatedThing(Double x, Double y,String fileName, Integer attitude) {


        this.x = x;
        this.y = y;


        this.fileName=fileName;
        this.sprite = new ImageView(new Image(fileName));

        index = 0;
        state = "running";
        prevState = "";
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
