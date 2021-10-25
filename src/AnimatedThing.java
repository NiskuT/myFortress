import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.Duration;

public abstract class AnimatedThing {

    private Double x;
    private Double vx;
    private Double ax;
    private Double y;

    private ImageView sprite;

    private Integer attitude;
    private String fileName;
    private Integer sens;


    private Integer index;
    private Integer indexMax;
    private long lastCall;

    private Integer winHeight = 400;
    private Integer winWidth = 600;

    public AnimatedThing(Double x, Double y,String fileName, Integer attitude) {
        this.x = x;
        vx=Double.valueOf(4);
        ax=Double.valueOf(0);
        this.y = winHeight-y-100-50;
        this.fileName=fileName;
        this.sprite = new ImageView(new Image(fileName));


        sprite.setViewport(new Rectangle2D(0,0,85,100));
        sprite.setX(this.x);
        sprite.setY(this.y);

        this.attitude = attitude;
        index = 0;
        indexMax=5;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void update(Double xCamera, long time){
        if( lastCall==0) lastCall = time;
        vx+=ax*(time-lastCall)*Math.pow(10,-9);
        x+=vx*(time-lastCall)*Math.pow(10,-9);

        index=(index+1)%indexMax;
        sprite.setViewport(new Rectangle2D(85*index,0,85,100));
        sprite.setX(this.x-xCamera);
        sprite.setY(this.y);
        lastCall=time;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
