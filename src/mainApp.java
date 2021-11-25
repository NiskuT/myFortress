import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainApp extends Application {
    private homePage menu;
    private settingScene param;
    private GameScene theScene;
    private int state =0;
    private KeyConfig clavier;

    @Override
    public void start(Stage primaryStage) throws Exception{

        clavier = new KeyConfig();

        primaryStage.setTitle("myFortress");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        Group root = new Group();
        menu = new homePage(root, 900,618);

        Group rootSett = new Group();
        param = new settingScene(rootSett, 900, 618, clavier);

        primaryStage.setScene(menu);

        AnimationTimer game = new AnimationTimer() {
            long lastTimerCall=0;
            final int timerate = 500000000;
            @Override
            public void handle(long now) {
                if( lastTimerCall + timerate < now){
                    lastTimerCall=now;
                    switch (state){
                        case 0 -> {
                            if(menu.isClose()) primaryStage.close();
                            else if (menu.isSetting()){
                                state = 1;
                                menu.resetState();
                                primaryStage.setScene(param);
                            }
                            else if (menu.isPlay()) {
                                state = 2;
                                menu.resetState();
                                Group rootGame = new Group();
                                theScene = new GameScene(rootGame, 900, 618, param.getConfig());
                                primaryStage.setScene(theScene);
                            }
                        }
                        case 1 -> {

                            if(param.isClose()) primaryStage.close();
                            else if (param.isMenu()){
                                state = 0;
                                param.resetState();
                                primaryStage.setScene(menu);
                            }
                        }
                        case 2 -> {
                            if(theScene.isClose()) primaryStage.close();
                            else if (theScene.isMenu()){
                                state = 0;
                                theScene.resetState();
                                primaryStage.setScene(menu);
                            }
                        }
                    }
                }
            }
        };

        game.start();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}