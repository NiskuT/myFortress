import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.Duration;

public abstract class AnimatedThing {

    private Double x;
    private Double y;

    private ImageView sprite;

    private Integer attitude;
    private String fileName;
    private Integer sens;

    private Integer index;
    private Integer indexMax;
    private Duration duration;

    private Integer winHeight = 400;
    private Integer winWidth = 600;

    public AnimatedThing(Double x, Double y,String fileName, Integer attitude) {
        this.x = x;
        this.y = winHeight-y-97;
        this.fileName=fileName;
        this.sprite = new ImageView(new Image(fileName));
        sprite.setViewport(new Rectangle2D(0,0,97,75));
        sprite.setX(this.x);
        sprite.setY(this.y);

        this.attitude = attitude;
    }

    public ImageView getSprite() {
        return sprite;
    }
}
