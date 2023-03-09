package test;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;

public class StaticConstraint implements Constraint
{
    private Point2D position;
    private Particle particle;

    public StaticConstraint(Particle particle)
    {
        this.position = particle.getPosition();
        this.particle = particle;
    }

    @Override
    public void satisfy()
    {
        particle.setPosition(position);
    }

    @Override
    public void draw(FXGraphics2D graphics)
    {

    }
}
