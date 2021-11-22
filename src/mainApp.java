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

    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setTitle("myFortress");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        Group root = new Group();
        homePage menu = new homePage(root, 600,400);

        primaryStage.setScene(menu);


        //Scene theScene = new GameScene(root, 800, 400);

        //primaryStage.setScene(theScene);




        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}