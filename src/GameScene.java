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
    private ArrayList<Foe> wolf = new ArrayList<>();
    private Group root;

    public GameScene(Group root, Integer length, Integer height, boolean z) {
        super(root,length,height,z);
        this.root = root;
        camera = new Camera(0.,0.);
        context = new staticThing(0.,0., 5);
        personnage = new Hero();

        keyboard();

        AnimationTimer timer = new AnimationTimer() {
            long lastTimerCall=0;
            //int timerate = 75000000;
            int timerate = 750000;
            @Override
            public void handle(long now) {
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
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    personnage.updateSprite();
                    shoot.forEach(Projectile::updateSprite);
                    wolf.forEach(Foe::updateSprite);

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

        if (wolf.size() < 4 && Math.random() < 0.02) {
            wolf.add(new Foe(personnage.getX() + 700 + (Math.random())*800));
            root.getChildren().add(wolf.get(wolf.size()-1).getSprite());
        }
    }

    private void updateEnemy(long now){
        ArrayList<Foe> toRemove = new ArrayList<>();
        wolf.forEach((loup) -> {
            if (personnage.getX() - loup.getX() > 300. || !loup.isAlive()){
                root.getChildren().remove(loup.getSprite());
                toRemove.add(loup);
            }
            else{
                loup.setxHero(personnage.getX());
                loup.update(camera.getX(), now);
            }
        });
        toRemove.forEach((ball)-> wolf.remove(ball));
    }

    private void checkCollision(){
        wolf.forEach((ennemy)->{
            shoot.forEach((ball)->{
                if (ball.isAlive() && ennemy.isAlive() && isInCollision(ennemy.getSprite(), ball.getSprite())){
                    ball.isDead();
                    ennemy.isDead();
                }
            });

            if(ennemy.isAlive() && isInCollision(ennemy.getSprite(), personnage.getSprite()) && !personnage.isInv()){
                context.getChildren().remove(context.getLives().get(context.getLives().size()));
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
            if (shoot.size() <2){
                shoot.add(new Projectile(personnage.getX(), personnage.getY(), personnage.isSprinting()));
                root.getChildren().add(shoot.get(shoot.size()-1).getSprite());
                personnage.shoot();
            }
        }
    }



}
