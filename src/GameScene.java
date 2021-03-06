import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    private KeyConfig config;

    private ProgressBar shootMana;
    private ImageView blood;

    private boolean isMenu = false;
    private boolean isClose = false;
    private Button menu;
    private Button leave;
    private ImageView croix;

    public GameScene(Group root, Integer length, Integer height, KeyConfig config) {
        super(root,length,height);
        this.root = root;
        this.config = config;
        camera = new Camera(0.,0.);
        context = new staticThing(0.,0., 5);
        personnage = new Hero();
        blood = new ImageView(new Image("ressources/BloodOverlay.png"));
        initCroix();

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
                    gameOver();
                }
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    personnage.updateSprite();
                    shoot.forEach(Projectile::updateSprite);
                    ennemies.forEach(AnimatedThing::updateSprite);
                }
            }
        };


        this.root.getChildren().add(context);
        this.root.getChildren().add(personnage.getSprite());
        initProgress();
        blood.setOpacity(0.);
        this.root.getChildren().add(blood);
        this.root.getChildren().add(croix);

        timer.start();
        spriteUpdate.start();


    }

    private void initProgress(){
        shootMana = new ProgressBar(1);
        shootMana.relocate(15,50);
        shootMana.setStyle(
                "-fx-text-box-border: black;" +
                "-fx-control-inner-background: black;" +
                        "-fx-background-color: transparent;" +
                        "-fx-accent: darkred;"
        );


        root.getChildren().add(shootMana);
    }

    private void updateAll(long now){
        checkCollision();
        updateEnemy(now);
        summonEnemy();
        handleKey();
        updateShoot(now);
        shootMana.setProgress(personnage.getProgress());
        personnage.update(camera.getX(),now);
        blood.setOpacity(personnage.getBlood());
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
        double z = 10;

        Bounds c  = a.getBoundsInParent();
        Bounds d  = b.getBoundsInParent();
        Rectangle2D area1 = new Rectangle2D(c.getMinX()+z, c.getMinY()+z,c.getWidth()-2*z,c.getHeight()-2*z);

        Rectangle2D area2 = new Rectangle2D(d.getMinX()+z, d.getMinY()+z,d.getWidth()-2*z,d.getHeight()-2*z);

        return area1.intersects(area2);
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
        if (currentlyActiveKeys.get(config.getJump())!=null && currentlyActiveKeys.get(config.getJump())) {
            personnage.jump();
            currentlyActiveKeys.put(config.getJump(), false);
        }
        if (currentlyActiveKeys.get(config.getSprint())!=null && currentlyActiveKeys.get(config.getSprint())) {
            personnage.sprint();
            currentlyActiveKeys.put(config.getSprint(), false);
        }
        if (currentlyActiveKeys.get(config.getShoot())!=null && currentlyActiveKeys.get(config.getShoot())) {
            currentlyActiveKeys.put(config.getShoot(), false);
            if (shoot.size() <2 && personnage.shoot()){
                shoot.add(new Projectile(personnage.getX(), personnage.getY(), personnage.isSprinting()));
                root.getChildren().add(shoot.get(shoot.size()-1).getSprite());
            }
        }
    }


    private void gameOver(){
        root.getChildren().remove(shootMana);
        root.getChildren().remove(croix);

        ImageView go = new ImageView(new Image("ressources/gameover.png"));
        go.setX(180);
        go.setY(35);
        menu = new Button("Menu");
        leave = new Button("Quitter");

        setButton();

        root.getChildren().add(go);
        root.getChildren().add(menu);
        root.getChildren().add(leave);

    }

    private void setButton(){
        menu.setLayoutX(160.);
        menu.setLayoutY(460.);
        leave.setLayoutX(500.);
        leave.setLayoutY(460.);
        menu.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));
        leave.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));

        String style1 = "-fx-font: 40 Impact;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-border-radius: 20, 20;";
        String style2 = "-fx-font: 40 Impact;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: darkred;"+
                "-fx-background-color: transparent;" +
                "-fx-border-color: darkred;" +
                "-fx-border-radius: 20, 20;";

        menu.setStyle(style1 );

        leave.setStyle(style1);

        menu.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                menu.setStyle(style2);
            }
        });
        menu.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                menu.setStyle(style1);
            }
        });

        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                isMenu=true;
            }
        });

        leave.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                leave.setStyle(style2);
            }
        });
        leave.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                leave.setStyle(style1);
            }
        });
        leave.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                isClose=true;
            }
        });

    }

    private void initCroix(){
        croix = new ImageView(new Image("ressources/croix.png"));
        croix.setX(860.);
        croix.setY(10.);
        croix.setOnMouseClicked((mouseEvent)-> isClose = true);
    }

    public boolean isMenu() {
        return isMenu;
    }

    public boolean isClose() {
        return isClose;
    }
    public void resetState(){
        isMenu=false;
        isClose=false;
    }
}
