import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class settingScene extends Scene {
    private boolean isMenu = false;
    private boolean isClose = false;
    private Group root;
    private Button menu;
    private Button leave;
    private staticThing back;

    private Button jump;
    private Button djump;
    private Button shoot;
    private Button sprint;

    private Text infos;

    private KeyConfig config;

    private int activeButton = 0;



    public settingScene(Group root, Integer length, Integer height, KeyConfig config) {
        super(root, length, height);
        this.root = root;
        this.config = config;

        init();
    }

    private void init(){
        back = new staticThing(0.,0.,0);
        root.getChildren().add( back);
        menu = new Button("Menu");
        leave = new Button("Quitter");

        jump = new Button("Sauter: "+config.getJump());
        djump = new Button("Double saut: 2 x "+config.getJump());
        shoot = new Button("Tirer: "+config.getShoot());
        sprint = new Button("Sprinter: "+config.getSprint());

        infos = new Text(530.,200.,"Cliquer sur un bouton puis entrez une touche pour modifier un paramètre.");

        setButton();
        setButtonConfig();
        keyboard();

        root.getChildren().add(menu);
        root.getChildren().add(leave);

        root.getChildren().addAll(jump,djump,shoot,sprint,infos);

    }

    private void keyboard(){

        this.setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (activeButton!=0) {
                switch (activeButton){
                    case 1 -> {
                        config.setJump(codeString);
                        jump.setText("Sauter: "+config.getJump());
                        djump.setText("Double saut: 2 x "+config.getJump());
                    }
                    case 2 -> {
                        config.setShoot(codeString);
                        shoot.setText("Tirer: "+config.getShoot());
                    }
                    case 3 -> {
                        config.setSprint(codeString);
                        sprint.setText("Sprinter: "+config.getSprint());
                    }
                }
            }
        });

        this.setOnKeyReleased(event -> activeButton=0 );
    }



    private void setButtonConfig(){
        jump.setLayoutX(160.);
        jump.setLayoutY(150.);
        djump.setLayoutX(160.);
        djump.setLayoutY(250.);
        shoot.setLayoutX(160.);
        shoot.setLayoutY(350.);
        sprint.setLayoutX(160.);
        sprint.setLayoutY(450.);

        String style = "-fx-font: 20 Arial;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: #969696;" +
                "-fx-border-color: transparent;" +
                "-fx-border-radius: 10, 10;" +
                "-fx-background-radius: 10,10";

        jump.setStyle(style);
        djump.setStyle(style);
        shoot.setStyle(style);
        sprint.setStyle(style);

        setEnteredAndExited(jump);
        setEnteredAndExited(djump);
        setEnteredAndExited(shoot);
        setEnteredAndExited(sprint);

        jump.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                activeButton=1;
            }
        });
        shoot.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                activeButton=2;
            }
        });
        sprint.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                activeButton=3;
            }
        });


    }
    private void setEnteredAndExited(Button bouton){
        String style = "-fx-font: 20 Arial;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: #969696;" +
                "-fx-border-color: transparent;" +
                "-fx-border-radius: 10, 10;" +
                "-fx-background-radius: 10,10";

        String style2 = "-fx-font: 20 Arial;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black;"+
                "-fx-background-color: #969696;" +
                "-fx-border-color: darkred;" +
                "-fx-border-radius: 10, 10;"+
                "-fx-background-radius: 10,10";

        bouton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                bouton.setStyle(style2);
            }
        });

        bouton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                bouton.setStyle(style);
            }
        });
    }


    private void setButton(){
        menu.setLayoutX(160.);
        menu.setLayoutY(50.);
        leave.setLayoutX(500.);
        leave.setLayoutY(50.);
        menu.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));
        leave.setEffect(new DropShadow(3, 3, 3, Color.web("#111111")));

        infos.setWrappingWidth(330.);
        infos.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        infos.setFill(Color.web("#A642FF"));

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

    public KeyConfig getConfig(){
        return config;
    }
}
