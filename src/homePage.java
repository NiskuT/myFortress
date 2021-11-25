import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class homePage extends Scene {
    private Group root;
    private Button play;
    private Button leave;
    private Button settings;

    private boolean isClose =false;
    private boolean isPlay = false;
    private boolean isSetting = false;
    private staticThing back;

    public homePage(Group root, Integer length, Integer height) {
        super(root, length, height);
        this.root = root;

        init();
    }

    private void init(){
        back = new staticThing(0.,0.,0);
        root.getChildren().add(back);
        play = new Button("Jouer");
        leave = new Button("Quitter");
        settings = new Button("Paramètres");
        setButton();

        root.getChildren().add(play);
        root.getChildren().add(leave);
        root.getChildren().add(settings);
    }

    private void setButton(){

        settings.setLayoutX(345.);
        settings.setLayoutY(350.);
        play.setLayoutX(180.);
        play.setLayoutY(250.);
        leave.setLayoutX(520.);
        leave.setLayoutY(250.);

        play.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));
        leave.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));
        settings.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));

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

        play.setStyle(style1);
        leave.setStyle(style1);
        settings.setStyle("-fx-font: 30 Impact;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: transparent;");

        play.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                play.setStyle(style2);
            }
        });
        play.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                play.setStyle(style1);
            }
        });

        play.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                isPlay=true;
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
        settings.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                settings.setStyle("-fx-font: 30 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: darkred;"+
                        "-fx-background-color: transparent;"
                );
            }
        });
        settings.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                settings.setStyle("-fx-font: 30 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: black;"+
                        "-fx-background-color: transparent;"
                );
            }
        });

        settings.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                isSetting=true;
            }
        });

    }

    public boolean isClose() {
        return isClose;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public boolean isSetting() {
        return isSetting;
    }
    public void resetState(){
        isSetting=false;
        isPlay=false;
        isClose=false;
    }
}
