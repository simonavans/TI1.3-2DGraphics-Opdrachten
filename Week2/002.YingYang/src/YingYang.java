import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        GeneralPath circle = new GeneralPath();
        circle.moveTo(0, 150);
        circle.curveTo(
                -200, 150,
                -200, -150,
                0, -150
        );
        circle.curveTo(
                200, -150,
                200, 150,
                0, 150
        );

        GeneralPath upperPath = new GeneralPath();
        upperPath.moveTo(0, 150);
        upperPath.curveTo(
                80,130,
                80,20,
                0, 0
        );
        upperPath.curveTo(
                -80, -20,
                -80, -130,
                0, -150
        );
        upperPath.curveTo(
                -200, -150,
                -200, 150,
                0, 150
        );

        graphics.setColor(Color.black);
        graphics.draw(circle);
        graphics.draw(upperPath);
        Area circleArea = new Area(circle);
        circleArea.subtract(new Area(upperPath));
        graphics.fill(circleArea);
        graphics.fill(new Ellipse2D.Double(0, 65, 20, 20));
        graphics.setColor(Color.white);
        graphics.fill(new Ellipse2D.Double(0, -85, 20, 20));
    }

    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
