import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class flyingFoe extends AnimatedThing {
    private Double xHero;

    public flyingFoe(Double x) {
        super(x, 170., "ressources/birdSprite.png", 10);

        vx = -2 * (85 / 1.70);

        state = "flying";
        sprite.setViewport(new Rectangle2D(0, 0, 73, 67));
        sprite.setX(this.x);
        sprite.setY(winHeight - this.y - 67 - 60); // 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center)
    }

    public void update(Double xCamera, long time){
        if(lastCall==0) lastCall = time;

        selectViewPort();
        x+=vx*(time-lastCall)*Math.pow(10,-9);

        sprite.setX(this.x-xCamera+50);
        sprite.setY(winHeight-this.y-67-60);
        lastCall=time;
    }


    private void selectViewPort(){
        if (index<=4) sprite.setViewport(new Rectangle2D(60 * index, 0, 60, 50));
        else sprite.setViewport(new Rectangle2D(60 * (index-5), 77, 60, 50));
    }

    public void updateSprite(){
        index--;
        if (index == -1) index = 9;
    }
}
