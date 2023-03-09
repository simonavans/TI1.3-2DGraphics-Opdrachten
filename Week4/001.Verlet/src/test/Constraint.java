package test;

import org.jfree.fx.FXGraphics2D;

public interface Constraint
{
    void satisfy();
    void draw(FXGraphics2D graphics);
}
