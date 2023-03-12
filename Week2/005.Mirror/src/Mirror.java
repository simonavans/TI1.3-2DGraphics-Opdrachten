import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.*;

public class Mirror extends Application {
    private Canvas canvas;
    private final Point2D normalVector = new Point2D.Double(1, 2.5);
    private final Renderable square = new Renderable(
            new Rectangle2D.Double(0, 0, 100, 100),
            new Point2D.Double(0, 150),
            0,
            1
    );

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Hello transforms");
        primaryStage.show();
    }

    public void draw(FXGraphics2D graphics) throws NoninvertibleTransformException {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale(1, -1);
        int scale = 300;
        Point2D scaledVector = new Point2D.Double(normalVector.getX() * scale, normalVector.getY() * scale);

        graphics.drawLine(0, -scale, 0, scale);
        graphics.drawLine(-scale, 0, scale, 0);
        graphics.setColor(Color.RED);
        graphics.drawLine(0, 0, (int) scaledVector.getX(), (int) scaledVector.getY());
        Point2D inverse = new AffineTransform().inverseTransform(new Point2D.Double(1, 2.5), null);
        graphics.setColor(Color.YELLOW);
        graphics.drawLine(0, 0, (int) inverse.getX() * scale, (int) inverse.getY() * scale);


        graphics.setColor(Color.BLUE);
        graphics.draw(square.getTransformedShape());
    }
}

class Renderable
{
    private Shape shape;
    private Point2D position;
    private float rotation;
    private float scale;

    public Renderable(Shape shape, Point2D position, float rotation, float scale)
    {
        this.shape = shape;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void draw(FXGraphics2D g2d)
    {
        g2d.draw(getTransformedShape());
    }

    public Shape getTransformedShape()
    {
        return getTransform().createTransformedShape(shape);
    }

    public AffineTransform getTransform()
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX(), position.getY());
        tx.rotate(rotation);
        tx.scale(scale,scale);
        return tx;
    }
}

