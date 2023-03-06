import org.jfree.fx.FXGraphics2D;

public interface GraphicsEngine {
    void setup();
    void update();
    void draw(FXGraphics2D graphics);
}
