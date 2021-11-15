import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;


public class GameScene extends Scene {
    private Camera camera;
    private Hero personnage;
    private staticThing context;
    private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();

    public GameScene(Group root, Integer length, Integer height, boolean z) {
        super(root,length,height,z);

        camera = new Camera(Double.valueOf(0),Double.valueOf(0));
        context = new staticThing(Double.valueOf(0),Double.valueOf(0), 5);
        personnage = new Hero();

        keyboard();

        AnimationTimer timer = new AnimationTimer() {
            long lastTimerCall=0;
            int timerate = 75000000;
            @Override
            public void handle(long now) {
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    handleKey();
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

    private void keyboard(){

        this.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
            }
        });

        this.setOnKeyReleased(event ->
                currentlyActiveKeys.remove(event.getCode().toString())
        );
    }

    private void handleKey(){
        if (currentlyActiveKeys.get("SPACE")!=null && currentlyActiveKeys.get("SPACE")) {
            personnage.jump();
            currentlyActiveKeys.put("SPACE", false);
        }
        if (currentlyActiveKeys.get("SHIFT")!=null && currentlyActiveKeys.get("SHIFT")) {
            personnage.sprint();
            currentlyActiveKeys.put("SHIFT", false);
        }
    }



}
