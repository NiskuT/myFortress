import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing{
    private Double vmax = 9. * (85 / 1.70);
    private Double vmin = 6. * (85 / 1.70);

    public Hero() {
        super(50., 0., "ressources/heros.png", 0);

        ax = 0.;
        vx = 6. * (85 / 1.70);

        ay = -9.81 * (85 / 1.70);
        vy = 0.;

        update(0.,0);// 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center the hero)

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

        if (vx>vmax){
            ax = -ax;
        }
        else if (vx<vmin){
            ax = 0.;
            vx = vmin;
        }

        selectViewPort();
        sprite.setX(this.x-xCamera+100);
        sprite.setY(winHeight-this.y-100-50);
        lastCall=time;
    }

    private void selectViewPort(){
        if (prevState.compareTo(state)!=0) index = 0;

        switch (state){
            case "running":
                indexMax = 5;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(85 * index, 0, 85, 100));
                break;

            case "runningAndShooting":
                indexMax = 5;
                index = (index + 1) % indexMax;
                sprite.setViewport(new Rectangle2D(8 * index, 324, 85, 100));
                break;

            case "jumpingUp":
                sprite.setViewport(new Rectangle2D(0, 163, 85, 100));
                break;

            case "jumpingDown":
                sprite.setViewport(new Rectangle2D(85, 163, 85, 100));
                break;

            case "jumpingUpAndShooting":
                sprite.setViewport(new Rectangle2D(0, 491, 85, 100));
                break;

            case "jumpingDownAndShooting":
                sprite.setViewport(new Rectangle2D(85, 491, 85, 100));
                break;

        }
        prevState = state;
    }

    public void jump() {
        if (y == 0) {
            vy = Double.valueOf(7 * (85 / 1.70));
            state = "jumpingUp";
        }

        else if (!doubleJump) {
            doubleJump = true;
            vy = Double.valueOf(5 * (85 / 1.70));
            state = "jumpingUp";
        }
    }

    public void sprint(){
        ax=1. * (85 / 1.70);
    }
}
