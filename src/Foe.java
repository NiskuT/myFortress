import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Foe extends AnimatedThing {
    private Double xHero;

    public Foe(Double x) {
        super(x, 0., "ressources/wolf.png", 6);

        vx = -2 * (85 / 1.70);

        this.sprite = new ImageView(new Image(fileName));

        state = "walking";
        sprite.setViewport(new Rectangle2D(0, 0, 110, 60));
        sprite.setX(this.x);
        sprite.setY(winHeight - this.y - 100 - 50); // 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center)


    }

    public void update(Double xCamera, long time){
        if(lastCall==0) lastCall = time;

        if (Math.abs(xHero-x)<400) state = "running";

        selectViewPort();
        x+=vx*(time-lastCall)*Math.pow(10,-9);

        sprite.setX(this.x-xCamera+50);
        sprite.setY(winHeight-this.y-60-50);
        lastCall=time;
    }


   private void selectViewPort(){
        if (prevState.compareTo(state)!=0) index = 0;

       switch (state) {
           case "walking" -> {
               indexMax = 6;
               sprite.setViewport(new Rectangle2D(110 * index, 0, 110, 60));
           }
           case "running" -> {
               vx = -5 * (85 / 1.70);
               indexMax = 5;
               sprite.setViewport(new Rectangle2D(110 * index, 60, 110, 60));
           }
       }
        prevState = state;
    }

    public void updateSprite(){
        index = (index+1)%indexMax;
    }

    public void setxHero(Double xHero) {
        this.xHero = xHero;
    }
}
