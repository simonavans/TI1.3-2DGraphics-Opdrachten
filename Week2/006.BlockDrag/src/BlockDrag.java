import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class BlockDrag extends Application {
    private FXGraphics2D graphics;
    private List<Renderable> renderables;
    private Renderable selectedRenderable;
    private Camera camera;

    @Override
    public void start(Stage primaryStage)
    {
        Canvas canvas = new Canvas(1920, 1080);
        this.camera = new Camera(new Point2D.Double(0, 0), this);
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.renderables = new ArrayList<>();
        this.renderables.add(new Renderable(
                new Rectangle2D.Double(-50, -50, 100, 100),
                new Point2D.Double(400, 400),
                Color.GREEN,
                graphics,
                this));
        this.renderables.add(new Renderable(
                new Rectangle2D.Double(-50, -50, 100, 100),
                new Point2D.Double(600, 400),
                Color.BLUE,
                graphics,
                this));


        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseReleased(e -> onMouseReleased());
        canvas.setOnMouseDragged(this::onMouseDragged);

        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Block drag");
        primaryStage.show();
    }

    private void onMousePressed(MouseEvent e)
    {
        for (Renderable renderable : renderables)
        {
            if (renderable.getTransformedShape().contains(new Point2D.Double(e.getX(), e.getY())))
            {
                System.out.println("found");
                selectedRenderable = renderable;
                return;
            }
        }

        camera.setStartTarget(new Point2D.Double(e.getX(), e.getY()));
    }

    private void onMouseReleased()
    {
        selectedRenderable = null;
    }

    private void onMouseDragged(MouseEvent e)
    {
        if (selectedRenderable != null)
        {
            selectedRenderable.setPosition(e.getX(), e.getY());
        }
        else
        {
            camera.moveTo(new Point2D.Double(e.getX(), e.getY()), graphics);
        }
    }

    public void redrawWorld()
    {
        for (Renderable renderable : renderables)
            renderable.redraw(graphics);
    }
}
