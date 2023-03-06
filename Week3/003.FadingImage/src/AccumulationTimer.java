import javafx.animation.AnimationTimer;
import org.jfree.fx.FXGraphics2D;

public class AccumulationTimer extends AnimationTimer {
    private long last = -1;
    private double accumulatedTime;

    @Override
    public void handle(long now) {
        if (last == -1) last = now;

        accumulatedTime += now - last;

        while (accumulatedTime > 1e9)
        {

        }
    }

    private void update(double deltaTime)
    {

    }

    private void draw(FXGraphics2D graphics)
    {

    }
}
