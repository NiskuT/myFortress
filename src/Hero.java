import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing{
    public Hero() {
        super(Double.valueOf(50), Double.valueOf(0), "ressources/heros.png", 0);

        ax = Double.valueOf(0);
        vx = Double.valueOf(6 * (85 / 1.70));

        ay = Double.valueOf(-9.81 * (85 / 1.70));
        vy = Double.valueOf(0);

        sprite.setViewport(new Rectangle2D(0, 0, 85, 100));
        sprite.setX(this.x);
        sprite.setY(winHeight - this.y - 100 - 50); // 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center the hero)

        indexMax = 5;
    }

    public void update(Double xCamera, long time){
        if( lastCall==0) lastCall = time;
        vx+=ax*(time-lastCall)*Math.pow(10,-9);
        x+=vx*(time-lastCall)*Math.pow(10,-9);

        vy+=ay*(time-lastCall)*Math.pow(10,-9);
        y+=vy*(time-lastCall)*Math.pow(10,-9);
        if (y<=0) {
            vy = Double.valueOf(0);
            y = Double.valueOf(0);
            doubleJump = false;
        }

        System.out.println(vy);
        if (y == 0) {
            index = (index + 1) % indexMax;
            sprite.setViewport(new Rectangle2D(85 * index, 0, 85, 100));
        }
        else if (vy>0){
            sprite.setViewport(new Rectangle2D(0, 163, 85, 100));
        }
        else if (vy<=0){
            sprite.setViewport(new Rectangle2D(85, 163, 85, 100));
        }
        sprite.setX(this.x-xCamera+100);
        sprite.setY(winHeight-this.y-100-50);
        lastCall=time;
    }

    public void jump() {
        if (y == 0) vy = Double.valueOf(7 * (85 / 1.70));

        else if (!doubleJump) {
            doubleJump = true;
            vy = Double.valueOf(5 * (85 / 1.70));
        }
    }
}
