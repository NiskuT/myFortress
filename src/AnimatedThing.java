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

    protected Integer winHeight = 618;
    protected Integer winWidth = 900;

    protected boolean doubleJump = false;
    private boolean alive = true;

    public AnimatedThing(Double x, Double y,String fileName, Integer indexMax) {


        this.x = x;
        this.y = y;


        this.fileName=fileName;
        this.sprite = new ImageView(new Image(fileName));

        index = 0;
        state = "running";
        prevState = "";
        this.indexMax = indexMax;
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

    public boolean isAlive(){
        return alive;
    }

    public void isDead(){
        alive = false;
    }

    public void updateSprite(){
        index = (index+1)%indexMax;
    }

    public void setxHero(Double xHero) {
    }


}
