import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Foe extends AnimatedThing {
    public Foe() {
        super(Double.valueOf(50), Double.valueOf(0), "ressources/heros.png", 0);

        ax = Double.valueOf(0);
        vx = Double.valueOf(6 * (85 / 1.70));
        this.x = x;

        ay = Double.valueOf(-9.81 * (85 / 1.70));
        vy = Double.valueOf(0);
        this.y = y;


        this.fileName = fileName;
        this.sprite = new ImageView(new Image(fileName));


        sprite.setViewport(new Rectangle2D(0, 0, 85, 100));
        sprite.setX(this.x);
        sprite.setY(winHeight - this.y - 100 - 50); // 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center the hero)

        this.attitude = attitude;
        index = 0;
        indexMax = 5;
    }

    public void update(Double xCamera, long time){

    }

    public void jump(){

    }

}
