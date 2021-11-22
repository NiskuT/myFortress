import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class settingScene extends Scene {
    private boolean isMenu = false;
    private boolean isClose = false;
    private Group root;
    private Button menu;
    private Button leave;

    public settingScene(Group root, Integer length, Integer height) {
        super(root, length, height);
        this.root = root;

        init();
    }

    private void init(){
        root.getChildren().add( new ImageView(new Image("ressources/desert.png")));
        menu = new Button("Menu");
        leave = new Button("Quitter");

        setButton();

        root.getChildren().add(menu);
        root.getChildren().add(leave);

    }

    private void setButton(){
        menu.setLayoutX(70.);
        menu.setLayoutY(175.);
        leave.setLayoutX(320.);
        leave.setLayoutY(175.);

        menu.setStyle("-fx-font: 40 Impact;" +
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

        menu.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                menu.setStyle("-fx-font: 40 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: yellow;"+
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 20, 20;"

                );
            }
        });
        menu.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                menu.setStyle("-fx-font: 40 Impact;" +
                        "-fx-font-weight: bold;"+
                        "-fx-text-fill: black;"+
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-radius: 20, 20;"

                );

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
