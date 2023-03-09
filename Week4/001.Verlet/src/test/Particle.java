package test;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle
{
    private Point2D position;
    private Point2D lastPosition;
    private final int canvasWidth;
    private final int canvasHeight;
    private final double size;

    public Particle(Point2D position, int canvasWidth, int canvasHeight)
    {
        this.position = position;
        this.lastPosition = position;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.size = 50;
    }

    public void update() {
        Point2D previous = position;
        Point2D velocity = new Point2D.Double(
                position.getX() - lastPosition.getX(),
                position.getY() - lastPosition.getY()
        );

        position = new Point2D.Double(
                position.getX() + velocity.getX(),
                position.getY() + velocity.getY() + 0.9
        );

        if (position.getX() < 0)
        {
            position = new Point2D.Double(0, position.getY());
        }
        else if (position.getX() > canvasWidth)
        {
            position = new Point2D.Double(canvasWidth, position.getY());
        }
        if (position.getY() < 0)
        {
            position = new Point2D.Double(position.getX(), 0);
        }
        else if (position.getY() > canvasHeight)
        {
            position = new Point2D.Double(position.getX(), canvasHeight);
        }

        lastPosition = previous;
    }

    public void draw(FXGraphics2D graphics)
    {
        Shape particleShape = new Ellipse2D.Double(
                position.getX() - (size/2d),
                position.getY() - (size/2d),
                size,
                size
        );
        graphics.setColor(Color.GREEN);
        graphics.fill(particleShape);
        graphics.setColor(Color.BLACK);
        graphics.draw(particleShape);
    }

    public Point2D getPosition()
    {
        return position;
    }

    public void setPosition(Point2D position)
    {
        this.position = position;
    }
}
