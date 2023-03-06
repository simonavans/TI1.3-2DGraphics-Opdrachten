import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Shape;

public class Mirror extends Application {

    Stage stage;
    ArrayList<Renderable> renderables = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        renderables.add(new Renderable(new Rectangle2D.Double(-50,-50,100,100), new Point2D.Double(400,400), 0.25f * (float)Math.PI, 0.75f));
        renderables.add(new Renderable(new Rectangle2D.Double(-50,-50,100,100), new Point2D.Double(600,400), -0.25f * (float)Math.PI, 1.75f));

        stage = primaryStage;
        javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Hello transforms");
        primaryStage.show();
    }

    public void draw(FXGraphics2D g2d) {

        for(Renderable r : renderables)
            r.draw(g2d);

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

