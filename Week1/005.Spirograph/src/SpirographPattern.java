import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Line2D;

public class SpirographPattern {
    private final FXGraphics2D graphics;
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    private final int red;
    private final int green;
    private final int blue;

    public SpirographPattern(FXGraphics2D graphics, double a, double b, double c, double d, int red, int green, int blue) {
        this.graphics = graphics;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void draw() {
        graphics.setColor(new Color(red, green, blue));

        double scale = 0.5;
        double lastX = a * Math.cos(0) + c * Math.cos(0);
        double lastY = a * Math.sin(0) + c * Math.sin(0);

        for (double theta = 0; theta < Math.PI*2.01; theta += 0.01) {
            double x = a * Math.cos(b * theta) + c * Math.cos(d * theta);
            double y = a * Math.sin(b * theta) + c * Math.sin(d * theta);

            graphics.draw(new Line2D.Double(
                    x*scale,
                    y*scale,
                    lastX*scale,
                    lastY*scale
            ));

            lastX = x;
            lastY = y;
        }
    }
}
