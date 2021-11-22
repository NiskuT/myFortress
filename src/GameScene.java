import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;


public class GameScene extends Scene {
    private Camera camera;
    private Hero personnage;
    private staticThing context;
    private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    private ArrayList<Projectile> shoot = new ArrayList<>();
    private ArrayList<AnimatedThing> ennemies = new ArrayList<>();
    private Group root;

    public GameScene(Group root, Integer length, Integer height) {
        super(root,length,height);
        this.root = root;
        camera = new Camera(0.,0.);
        context = new staticThing(0.,0., 5);
        personnage = new Hero();

        keyboard();

        AnimationTimer timer = new AnimationTimer() {
            long lastTimerCall=0;
            int timerate = 750000;
            @Override
            public void handle(long now) {
                if(!personnage.isAlive()) stop();
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    updateAll(now);
                }
            }
        };

        AnimationTimer spriteUpdate = new AnimationTimer() {
            long lastTimerCall=0;
            int timerate = 50000000;
            @Override
            public void handle(long now) {
                if(!personnage.isAlive()){
                    stop();
                    // fct mort
                }
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    personnage.updateSprite();
                    shoot.forEach(Projectile::updateSprite);
                    ennemies.forEach(AnimatedThing::updateSprite);
                }
            }
        };

        timer.start();
        spriteUpdate.start();

        this.root.getChildren().add(context);
        this.root.getChildren().add(personnage.getSprite());



    }
    private void updateAll(long now){
        checkCollision();
        updateEnemy(now);
        summonEnemy();
        handleKey();
        updateShoot(now);
        personnage.update(camera.getX(),now);
        camera.update(now, personnage.getX());
        context.update(camera.getX(), 0.);
    }

    private void summonEnemy(){

        if (ennemies.size() < 10 && Math.random() < 0.02) {

            if(Math.random() < 0.4) ennemies.add(new Foe(personnage.getX() + 700 + (Math.random())*800));
            else ennemies.add(new flyingFoe(personnage.getX() + 700 + (Math.random())*800));

            root.getChildren().add(ennemies.get(ennemies.size()-1).getSprite());
        }
    }

    private void updateEnemy(long now){
        ArrayList<AnimatedThing> toRemove = new ArrayList<>();
        ennemies.forEach((enemy) -> {
            if (personnage.getX() - enemy.getX() > 300. || !enemy.isAlive()){
                root.getChildren().remove(enemy.getSprite());
                toRemove.add(enemy);
            }
            else{
                enemy.setxHero(personnage.getX());
                enemy.update(camera.getX(), now);
            }
        });
        toRemove.forEach((enemy)-> ennemies.remove(enemy));

    }

    private void checkCollision(){

        ennemies.forEach((enemy)->{
            shoot.forEach((ball)->{
                if (ball.isAlive() && enemy.isAlive() && isInCollision(enemy.getSprite(), ball.getSprite())){
                    ball.isDead();
                    enemy.isDead();
                }
            });

            if(enemy.isAlive() && isInCollision(enemy.getSprite(), personnage.getSprite()) && !personnage.isInv() && personnage.isAlive()){
                context.getChildren().remove(context.getLives().get(context.getLives().size()-1));
                if (context.removeLastLife()) personnage.isDead();
                else{
                    personnage.hit();
                }
            }
        });
    }

    private Boolean isInCollision(ImageView a, ImageView b){
        return a.getBoundsInParent().intersects(b.getBoundsInParent());
    }

    private void updateShoot(long now){
        ArrayList<Projectile> toRemove = new ArrayList<>();
        shoot.forEach((ball) -> {
            if (ball.getFinish() || !ball.isAlive()){
                root.getChildren().remove(ball.getSprite());
                toRemove.add(ball);
            }
            else{
                ball.update(camera.getX(), now);
            }
        });
        toRemove.forEach((ball)-> shoot.remove(ball));
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
        if (currentlyActiveKeys.get("ALT")!=null && currentlyActiveKeys.get("ALT")) {
            currentlyActiveKeys.put("ALT", false);
            if (shoot.size() <2 && personnage.shoot()){
                shoot.add(new Projectile(personnage.getX(), personnage.getY(), personnage.isSprinting()));
                root.getChildren().add(shoot.get(shoot.size()-1).getSprite());
            }
        }
    }



}
