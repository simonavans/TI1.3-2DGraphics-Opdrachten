package test;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle
{
    private Point2D position;
    private Point2D speed;
    private Point2D acceleration;

    public Particle(Point2D position)
    {
        this.position = position;
        this.speed = new Point2D.Double(0, 0);
        this.acceleration = new Point2D.Double(0, -9.8);
    }

    public void update() {
        this.position = new Point2D.Double(
                position.getX() + speed.getX(),
                position.getY() + speed.getY()
        );
        this.speed = new Point2D.Double(
                speed.getX() + acceleration.getX(),
                speed.getY() + acceleration.getY()
        );
    }

    public void draw(FXGraphics2D graphics)
    {
        graphics.fill(new Ellipse2D.Double(
                position.getX() - 5,
                position.getY() - 5,
                10,
                10
        ));
    }
}
