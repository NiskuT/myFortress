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

    private ArrayList<ImageView> background1 = new ArrayList<>();
    private ArrayList<ImageView> background2 = new ArrayList<>();


    public staticThing(Double x, Double y, Integer numberOfLives){
        super();
        this.x=x;
        this.y=y;

        for (Integer k = 0; k<12; k++){
            background1.add(new ImageView(new Image("/layers/layer"+k.toString()+".png")));
            background2.add(new ImageView(new Image("/layers/layer"+k.toString()+".png")));
        }
        heightBackground = new Image("/layers/layer1.png").getHeight();
        lengthBackground = new Image("/layers/layer1.png").getWidth();
/*
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

*/

        Image coeur = new Image(heart);
        lives = new ArrayList<>();

        for (int k=0; k<numberOfLives;k++){
            lives.add(new ImageView(coeur) );
            lives.get(k).setPreserveRatio(true);
            lives.get(k).setFitWidth(25);
            lives.get(k).setViewport(new Rectangle2D(0,0,lives.get(k).getFitWidth(),lives.get(k).getFitHeight()));
            lives.get(k).setX(10+k*(lives.get(k).getFitWidth()+5));
            lives.get(k).setY(10+lives.get(k).getFitHeight());
        }

        //this.getChildren().add(backRight);
        //this.getChildren().add(backLeft);

        for (int k =11; k >=0; k--){
            background1.get(k).setViewport(new Rectangle2D(0,175, lengthBackground, heightBackground-175));
            background1.get(k).setX(-this.x*(0.1f*(11-k)));
            background1.get(k).setY(this.y);
            background2.get(k).setViewport(new Rectangle2D(0,175, lengthBackground, heightBackground-175));
            background2.get(k).setX(lengthBackground -this.x*(0.1f*(11-k)));
            background2.get(k).setY(this.y);
            this.getChildren().add(background1.get(k));
            this.getChildren().add(background2.get(k));
        }



        this.getChildren().addAll(lives);



    }

    public void update(Double x, Double y){
        this.x = x;
        this.y = y;
        this.updateCoor();

    }
    private void updateCoor(){

        for (int k = 0; k < 12; k++){
            background1.get(k).setX(-(this.x*(0.1f*(11-k)))%lengthBackground);

            background2.get(k).setX(lengthBackground -(this.x*(0.1f*(11-k)))%lengthBackground);
        }
    }

/*
    public void update(Double x, Double y){
        this.x = x % lengthBackground;
        this.y = y;
        this.updateCoor();

    }

    private void updateCoor(){
        backLeft.setX(-this.x);
        backLeft.setY(this.y);
        backRight.setX(lengthBackground -this.x);
        backRight.setY(this.y);
    }
*/
    public ArrayList<ImageView> getLives() {
        return lives;
    }

    public boolean removeLastLife() {
        lives.remove(lives.size()-1);
        return lives.size() == 0;
    }
}
