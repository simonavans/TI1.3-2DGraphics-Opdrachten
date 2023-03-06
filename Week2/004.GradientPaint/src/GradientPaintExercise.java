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

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.setMinHeight(1080/3f);
        primaryStage.setMinWidth(1920/3f);
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Point2D center = new Point2D.Double(canvas.getWidth()/2, canvas.getHeight()/2);

        RadialGradientPaint rgp = new RadialGradientPaint(center, 20f, center,
                new float[]{ 0.5f, 0.7f },
                new Color[]{Color.black, Color.red}, MultipleGradientPaint.CycleMethod.REFLECT);

        graphics.setPaint(rgp);
        graphics.fill(new Rectangle2D.Double(0,0,1920, 1080));

        canvas.setOnMouseDragged(e -> {
            graphics.setPaint(new RadialGradientPaint(center, 20f, new Point2D.Double(e.getSceneX(), e.getSceneY()),
                    new float[]{ 0.5f, 0.7f },
                    new Color[]{Color.black, Color.red}, MultipleGradientPaint.CycleMethod.REFLECT));
            graphics.fill(new Rectangle2D.Double(0,0,1920, 1080));
        });
    }


    public static void main(String[] args)
    {
        launch(GradientPaintExercise.class);
    }

}
