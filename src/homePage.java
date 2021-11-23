import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public homePage(Group root, Integer length, Integer height) {
        super(root, length, height);
        this.root = root;

        init();
    }

    private void init(){
        root.getChildren().add( new ImageView(new Image("ressources/desert.png")));
        play = new Button("Jouer");
        leave = new Button("Quitter");
        settings = new Button("Paramètres");
        setButton();

        root.getChildren().add(play);
        root.getChildren().add(leave);
        root.getChildren().add(settings);
    }

    private void setButton(){

        play.setLayoutX(90.);
        play.setLayoutY(135.);
        leave.setLayoutX(340.);
        leave.setLayoutY(135.);
        settings.setLayoutX(190.);
        settings.setLayoutY(230.);

        play.setStyle("-fx-font: 40 Impact;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-border-radius: 20, 20;"
        );

        leave.setStyle("-fx-font: 40 Impact;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-border-radius: 20, 20;"
        );

        settings.setStyle("-fx-font: 30 Impact;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: transparent;"
        );

        play.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                play.setStyle("-fx-font: 40 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: yellow;"+
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 20, 20;"

                );
            }
        });
        play.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                play.setStyle("-fx-font: 40 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: black;"+
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-radius: 20, 20;"

                );

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
                leave.setStyle("-fx-font: 40 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: yellow;"+
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 20, 20;"

                );
            }
        });
        leave.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                leave.setStyle("-fx-font: 40 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: black;"+
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-radius: 20, 20;"

                );
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
                        "-fx-text-fill: yellow;"+
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
