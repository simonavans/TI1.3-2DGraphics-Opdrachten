import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Renderable
{
    private final Shape rawShape;
    private AffineTransform transform;
    private Point2D position;
    private Color color;
    private BlockDrag callback;

    public Renderable(Shape rawShape, Point2D position, Color color, FXGraphics2D graphics, BlockDrag callback)
    {
        this.rawShape = rawShape;
        this.transform = new AffineTransform();
        this.position = position;
        this.color = color;
        this.callback = callback;

        this.transform.translate(position.getX(), position.getY());
        redraw(graphics);
    }

    public void redraw(FXGraphics2D graphics)
    {
        graphics.setColor(Color.WHITE);
        graphics.draw(getTransformedShape());
        graphics.fill(getTransformedShape());

        this.transform = new AffineTransform();
        this.transform.translate(position.getX(), position.getY());

        graphics.setColor(color);
        graphics.fill(getTransformedShape());
    }

    public void setPosition(double x, double y)
    {
        this.position = new Point2D.Double(x, y);
        callback.redrawWorld();
    }

    public Shape getTransformedShape()
    {
        return transform.createTransformedShape(rawShape);
    }

    public Color getColor()
    {
        return color;
    }
}

