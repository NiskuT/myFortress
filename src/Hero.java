import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing{
    private Double vmax = 9. * (85 / 1.70);
    private Double vmin = 6. * (85 / 1.70);
    private int timeShooting = 20;
    private int imShooting = timeShooting;
    private Integer invincibilityTime = 0;

    private long actualTime = 0;
    private long lastTimeShoot = 0;
    private long freqOfShoot = 500000000;


    public Hero() {
        super(50., 0., "ressources/heros.png", 6);

        ax = 0.;
        vx = 6. * (85 / 1.70);

        ay = -9.81 * (85 / 1.70);
        vy = 0.;

        update(0.,0);// 0 is up so winHeight - y is height (+100 -> sprite height) (+50 center the hero)


    }

    public void update(Double xCamera, long time){
        if( lastCall==0) lastCall = time;
        updateTime(time);
        if (invincibilityTime!=0) invincibilityTime--;
        vx+=ax*(time-lastCall)*Math.pow(10,-9);
        x+=vx*(time-lastCall)*Math.pow(10,-9);

        vy+=ay*(time-lastCall)*Math.pow(10,-9);
        y+=vy*(time-lastCall)*Math.pow(10,-9);

        if (y<=0) {
            vy = 0.;
            y = 0.;
            doubleJump = false;
        }

        if (vx>vmax){
            ax = -ax;
        }
        else if (vx<vmin){
            ax = 0.;
            vx = vmin;
        }

        selectViewPort();
        sprite.setX(this.x-xCamera);
        sprite.setY(winHeight-this.y-100-50);
        lastCall=time;
    }

    private void updateTime(long t){
        actualTime=t;
    }

    private void selectViewPort(){
        if (prevState.compareTo(state)!=0 && !state.contains("Shooting")) index = 0;

        switch (state) {
            case "running" -> {
                //index = (index + 1) % indexMax;
                indexMax = 6;
                sprite.setViewport(new Rectangle2D(85 * index, 0, 85, 100));
            }
            case "runningAndShooting" -> {
                indexMax = 6;
                sprite.setViewport(new Rectangle2D(85 * index, 324, 85, 100));
                imShooting--;
                if (imShooting <= 0) {
                    imShooting = timeShooting;
                    state = "running";
                }
            }
            case "jumpingUp" -> {
                indexMax = 1;
                sprite.setViewport(new Rectangle2D(0, 163, 85, 100));
                if (vy < 0) {
                    state = "jumpingDown";
                }
            }
            case "jumpingDown" -> {
                indexMax = 1;
                sprite.setViewport(new Rectangle2D(85, 163, 85, 100));
                if (y == 0) state = "running";
            }
            case "jumpingUpAndShooting" -> {
                indexMax = 1;
                sprite.setViewport(new Rectangle2D(0, 491, 85, 100));
                imShooting--;
                if (imShooting != 0 && vy < 0) {
                    state = "jumpingDownAndShooting";
                } else if (imShooting == 0) {
                    imShooting = timeShooting;
                    state = "jumpingUp";
                } else if (vy < 0) {
                    imShooting = timeShooting;
                    state = "jumpingDown";
                }
            }
            case "jumpingDownAndShooting" -> {
                indexMax = 1;
                imShooting--;
                sprite.setViewport(new Rectangle2D(85, 491, 85, 100));
                if (y == 0 && imShooting != 0) state = "runningAndShooting";
                else if (y == 0) {
                    imShooting = timeShooting;
                    state = "running";
                } else if (imShooting <= 0) {
                    imShooting = timeShooting;
                    state = "jumpingDown";
                }
            }
        }
        prevState = state;
    }

    public void updateSprite(){
        index = (index+1)%indexMax;
    }

    public void jump() {
        if (y == 0) {
            vy = 7 * (85 / 1.70);
            if (state.compareTo("runningAndShooting")==0)  state = "jumpingUpAndShooting";
            else if(state.compareTo("running")==0)  state = "jumpingUp";
        }

        else if (!doubleJump) {
            doubleJump = true;
            vy = 5 * (85 / 1.70);
            if (state.compareTo("jumpingUpAndShooting")==0 || state.compareTo("jumpingDownAndShooting")==0)  state = "jumpingUpAndShooting";
            else state = "jumpingUp";
        }
    }

    public void sprint(){
        ax=1. * (85 / 1.70);
    }

    public Boolean isSprinting(){ return ax > 0.; }

    public boolean shoot(){

        if (actualTime -lastTimeShoot < freqOfShoot) return false;
        else {
            if (state.compareTo("running") == 0) state = "runningAndShooting";
            else if (state.compareTo("jumpingUp") == 0) state = "jumpingUpAndShooting";
            else if (state.compareTo("jumpingDown") == 0) state = "jumpingDownAndShooting";
            lastTimeShoot=actualTime;
            return true;
        }

    }

    public boolean isInv(){
        return invincibilityTime != 0;
    }

    public void hit(){
        invincibilityTime = 40;
    }
}
