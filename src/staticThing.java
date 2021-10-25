import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class staticThing extends Pane{
    private Double x;
    private Double y;

    private Double lengthImage;
    private Double heightImage;

    private ImageView backRight;
    private ImageView backLeft;

    public staticThing(Double x, Double y,String back){
        this.x=x;
        this.y=y;

        Image myImage = new Image(back);
        heightImage = myImage.getHeight();
        lengthImage = myImage.getWidth();

        ImageView backRight = new ImageView(myImage);
        backRight.setViewport(new Rectangle2D(0,0, lengthImage, heightImage));
        backRight.setX(lengthImage +this.x);
        backRight.setY(this.y);

        ImageView backLeft = new ImageView(myImage);
        backLeft.setViewport(new Rectangle2D(0,0, lengthImage, heightImage));
        backLeft.setX(this.x);
        backLeft.setY(this.y);


        this.getChildren().add(backRight);
        this.getChildren().add(backLeft);

    }

}
