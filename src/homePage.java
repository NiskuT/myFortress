import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class homePage extends Scene {
    private Group root;
    private Button play;
    private Button leave;

    public homePage(Group root, Integer length, Integer height) {
        super(root, length, height);
        this.root = root;

        init();
    }

    private void init(){
        root.getChildren().add( new ImageView(new Image("ressources/desert.png")));
        play = new Button("Jouer");
        leave = new Button("Quitter");
        setButton();

        root.getChildren().add(play);
        root.getChildren().add(leave);
    }

    private void setButton(){

        play.setLayoutX(150.);
        play.setLayoutY(175.);
        leave.setLayoutX(350.);
        leave.setLayoutY(200.);

        play.setStyle("-fx-font: 40 impact;" +
                //"-fx-base: #1d1d1d;" //+
                "-fx-background-color: transparent;" +
                "-fx-border-color: white, white;" +
                "-fx-border-radius: 15, 15;"

);

        play.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("là");
            }
        });

        leave.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("ici");
            }
        });

    }
}
