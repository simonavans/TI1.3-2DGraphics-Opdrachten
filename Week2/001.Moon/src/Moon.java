import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Moon extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Moon");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        GeneralPath moon = new GeneralPath();
        moon.moveTo(0, 200);
        moon.curveTo(
                100, 170,
                100, 30,
                0, 0
        );

        GeneralPath moonSubtract = new GeneralPath();
        moonSubtract.moveTo(0, 200);
        moonSubtract.curveTo(
                50, 170,
                50, 30,
                0, 0
        );

        Area moonArea = new Area(moon);
        Area moonSubtractArea = new Area(moonSubtract);
        moonArea.subtract(moonSubtractArea);

        graphics.setColor(Color.yellow);
        graphics.fill(moonArea);
        graphics.setColor(Color.black);
        graphics.draw(moonArea);
    }

    public static void main(String[] args)
    {
        launch(Moon.class);
    }
}
