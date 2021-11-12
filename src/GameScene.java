import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GameScene extends Scene {
    private Camera camera;

    public GameScene(Group root, Integer length, Integer height, boolean z) {
        super(root,length,height,z);
        camera = new Camera(Double.valueOf(0),Double.valueOf(0));

        staticThing context = new staticThing(Double.valueOf(0),Double.valueOf(0), 5);
        Hero personnage = new Hero();

        this.setOnMouseClicked( (event)->{
            personnage.jump();
        });



        AnimationTimer timer = new AnimationTimer() {
            long lastTimerCall=0;
            int timerate = 75000000;
            @Override
            public void handle(long now) {
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    personnage.update(camera.getX(),now);
                    camera.update(now, Double.valueOf(personnage.getX()));
                    context.update(camera.getX(), Double.valueOf(0));


                }
            }
        };
        timer.start();



        root.getChildren().add(context);
        root.getChildren().add(personnage.getSprite());


    }

    public void update(){
        System.out.println("dd");
    }

}
