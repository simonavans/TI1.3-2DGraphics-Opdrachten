import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Spotlight extends Application implements GraphicsEngine
{
    private ResizableCanvas canvas;
    private Point2D mousePosition;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);

        setup();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");
        stage.show();
    }

    @Override
    public void setup()
    {
        mousePosition = new Point2D.Double(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.setOnMouseMoved(e ->
        {
            mousePosition.setLocation(e.getX(), e.getY());
        });

        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        graphics.setPaint(Color.BLACK);
        new AccumulationTimer(60, this, graphics);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());

        Shape shape = new Ellipse2D.Double(mousePosition.getX() - 100, mousePosition.getY() - 100, 200, 200);
        AffineTransform transform = new AffineTransform();
        graphics.draw(transform.createTransformedShape(shape));
        graphics.clip(shape);

        Random r = new Random();
        for(int i = 0; i < 2; i++)
        {
            graphics.setPaint(Color.getHSBColor(r.nextFloat(),1,1));
            graphics.drawLine((int) (r.nextInt() % canvas.getWidth()), (int) (r.nextInt() % canvas.getHeight()), (int) (r.nextInt() % canvas.getWidth()), (int) (r.nextInt() % canvas.getHeight()));
        }
    }

    public static void main(String[] args)
    {
        launch(Spotlight.class);
    }

}
