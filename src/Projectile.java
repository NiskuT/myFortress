import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends AnimatedThing{
    private int timeBeforeExplosion = 8;

    public Projectile(Double x, Double y, Boolean sprint) {
        super(x+85, y+50, "ressources/shooting.png", 5);

        ax = 0.7* (85 / 1.70);
        vx = 16. * (85 / 1.70);

        this.sprite = new ImageView(new Image(fileName));

        index = 0;
        indexMax = 5;

        if (sprint) state = "sprint";
        else state = "classic";
    }

    public void update(Double xCamera, long time){
        if( lastCall==0) lastCall = time;
        System.out.println(x);
        if (state.compareTo("explose")!=0) {

            vx += ax * (time - lastCall) * Math.pow(10, -9);
            x += vx * (time - lastCall) * Math.pow(10, -9);
        }

        if (timeBeforeExplosion==0) state = "explose";

        selectViewPort(xCamera);

        lastCall=time;
        timeBeforeExplosion--;
    }

    private void selectViewPort(Double xCamera){

        if (prevState.compareTo(state)!=0) index = 0;

        switch (state){
            case "classic":
                indexMax = 3;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(40 * index, 0, 40, 40));
                sprite.setX(this.x-xCamera+50);
                sprite.setY(winHeight-this.y-20-50);
                break;

            case "sprint":
                indexMax = 3;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(90 * index, 40, 90, 60));
                sprite.setX(this.x-xCamera+50);
                sprite.setY(winHeight-this.y-30-50);
                break;

            case "explose":
                indexMax = 6;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(70*index, 100, 70, 70));
                sprite.setX(this.x-xCamera+35);
                sprite.setY(winHeight-this.y-35-50);
                break;


        }
        prevState = state;
    }
}
