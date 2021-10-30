import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class staticThing extends Pane{
    private Double x;
    private Double y;

    private Double lengthBackground;
    private Double heightBackground;

    private ImageView backRight;
    private ImageView backLeft;

    private String back = "ressources/desert.png";
    private String heart = "ressources/coeur.png";

    private ArrayList<ImageView> lives;


    public staticThing(Double x, Double y, Integer numberOfLives){
        this.x=x;
        this.y=y;

        Image myImage = new Image(back);
        heightBackground = myImage.getHeight();
        lengthBackground = myImage.getWidth();

        backRight = new ImageView(myImage);
        backRight.setViewport(new Rectangle2D(0,0, lengthBackground, heightBackground));
        backRight.setX(lengthBackground +this.x);
        backRight.setY(this.y);

        backLeft = new ImageView(myImage);
        backLeft.setViewport(new Rectangle2D(0,0, lengthBackground, heightBackground));
        backLeft.setX(this.x);
        backLeft.setY(this.y);



        Image coeur = new Image(heart);
        lives = new ArrayList<ImageView>();

        for (int k=0; k<numberOfLives;k++){
            lives.add(new ImageView(coeur) );
            lives.get(k).setPreserveRatio(true);
            lives.get(k).setFitWidth(25);
            lives.get(k).setViewport(new Rectangle2D(0,0,lives.get(k).getFitWidth(),lives.get(k).getFitHeight()));
            lives.get(k).setX(10+k*(lives.get(k).getFitWidth()+5));
            lives.get(k).setY(10+lives.get(k).getFitHeight());
        }

        this.getChildren().add(backRight);
        this.getChildren().add(backLeft);

        this.getChildren().addAll(lives);



    }

    public void update(Double x, Double y){
        this.x = Double.valueOf(x%lengthBackground);
        this.y = y;
        this.updateCoor();

    }

    private void updateCoor(){
        backLeft.setX(-this.x);
        backLeft.setY(this.y);
        backRight.setX(lengthBackground -this.x);
        backRight.setY(this.y);
    }

}
