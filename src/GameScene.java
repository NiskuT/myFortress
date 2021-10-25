import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private Camera camera;

    public GameScene(Group root, Integer length, Integer height, boolean z) {
        super(root,length,height,z);
        camera = new Camera(0,0);

        staticThing context = new staticThing(Double.valueOf(0),Double.valueOf(0),"ressources/desert.png");



        root.getChildren().add(context);


    }

}
