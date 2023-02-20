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

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Color[] colors = { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray,
        Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red,
        Color.white, Color.yellow };

//        addNeighboringSquare(graphics, Color.blue, 0);

        for (int i = 0; i < colors.length; i++) {
            addNeighboringSquare(graphics, colors[i], i);
        }
    }

    private void addNeighboringSquare(FXGraphics2D graphics, Color color, int squaresDrawn) {
        int edgeLength = (int) canvas.getWidth() / 13;

        graphics.setColor(color);
        graphics.fillRect(
                (squaresDrawn * edgeLength),
                0,
                edgeLength, edgeLength
        );
    }


    public static void main(String[] args)
    {
        launch(Colors.class);
    }

}
