import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends AnimatedThing{
    private int timeBeforeExplosion = 400000000;
    private Boolean finish = false;
    private long actualTime = 0;
    private long timeStart = 0;

    public Projectile(Double x, Double y, Boolean sprint) {
        super(x+85, y+50, "ressources/shooting.png", 5);

        ax = 0.7* (85 / 1.70);
        vx = 16. * (85 / 1.70);


        index = 0;
        indexMax = 5;

        if (sprint) state = "sprint";
        else state = "classic";
    }

    public void update(Double xCamera, long time){
        if( lastCall==0) lastCall = time;
        if(timeStart==0) timeStart = time;
        updateTime(time);

        if (state.compareTo("explose")!=0) {

            vx += ax * (time - lastCall) * Math.pow(10, -9);
            x += vx * (time - lastCall) * Math.pow(10, -9);
        }

        if (actualTime-timeStart>timeBeforeExplosion) state = "explose";

        selectViewPort(xCamera);

        lastCall=time;
    }

    private void selectViewPort(Double xCamera){

        if (prevState.compareTo(state)!=0) index = 0;

        switch (state) {
            case "classic" -> {
                indexMax = 3;
                sprite.setViewport(new Rectangle2D(40 * index, 0, 40, 40));
                sprite.setX(this.x - xCamera + 50);
                sprite.setY(winHeight - this.y - 20 - 50);
            }
            case "sprint" -> {
                indexMax = 3;
                sprite.setViewport(new Rectangle2D(90 * index, 40, 90, 60));
                sprite.setX(this.x - xCamera + 50);
                sprite.setY(winHeight - this.y - 30 - 50);
            }
            case "explose" -> {
                indexMax = 7;
                if (index >= indexMax - 1) {
                    finish = true;
                    break;
                }
                sprite.setViewport(new Rectangle2D(70 * index, 100, 70, 70));
                sprite.setX(this.x - xCamera + 35);
                sprite.setY(winHeight - this.y - 35 - 50);
            }
        }
        prevState = state;
    }

    private void updateTime(long t){
        actualTime=t;
    }



    public Boolean getFinish() {
        return finish;
    }

    @Override
    public void isDead(){
        state="explose";
    }

}
