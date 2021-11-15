import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends AnimatedThing{
    public Projectile() {
        super(50., 0., "ressources/shooting.png", 0);

        ax = 0.5* (85 / 1.70);
        vx = 8. * (85 / 1.70);
        this.x = x;
        this.y = y;

        this.fileName = fileName;
        this.sprite = new ImageView(new Image(fileName));


        sprite.setX(this.x);
        sprite.setY(winHeight - this.y - 100 - 50); // 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center the hero)

        index = 0;
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
            state = "running";
        }

        if (vy<0){
            state = "jumpingDown";
        }



        selectViewPort();
        sprite.setX(this.x-xCamera+100);
        sprite.setY(winHeight-this.y-100-50);
        lastCall=time;
    }

    private void selectViewPort(){

        if (prevState.compareTo(state)!=0) index = 0;

        switch (state){
            case "classic":
                indexMax = 3;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(40 * index, 0, 40, 40));
                break;

            case "sprint":
                indexMax = 3;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(90 * index, 40, 90, 60));
                break;

            case "explose":
                indexMax = 6;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(70*index, 100, 70, 70));
                break;


        }
        prevState = state;
    }
}
