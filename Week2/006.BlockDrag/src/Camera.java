import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private Point2D startTarget;
    private BlockDrag callback;

    public Camera(Point2D target, BlockDrag callback) {
        this.startTarget = target;
        this.callback = callback;
    }

    public void setStartTarget(Point2D startTarget)
    {
        this.startTarget = startTarget;
    }

    public void moveTo(Point2D target, FXGraphics2D graphics)
    {
        Point2D diffTarget = new Point2D.Double(target.getX() - startTarget.getX(), target.getY() - startTarget.getY());
        AffineTransform transform = new AffineTransform();
        transform.translate(diffTarget.getX(), diffTarget.getY());
        graphics.translate(diffTarget.getX(), diffTarget.getY());
        callback.redrawWorld();
        startTarget = target;
    }
}
