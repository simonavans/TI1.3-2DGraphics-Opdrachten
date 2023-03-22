import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Screensaver extends Application
{
    private ResizableCanvas canvas;
    private ArrayList<Vertex> vertices;
    private List<Line2D> lines;
    private float lineColor;

    @Override
    public void init()
    {
        vertices = new ArrayList<>(Arrays.asList(
                new Vertex(-500, 100, -1, 1),
                new Vertex(-100, 100, -1, -1),
                new Vertex(-100, -300, 1, 1),
                new Vertex(-500, -299, 1, -1)
        ));
        lines = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer()
        {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Screensaver");
        stage.show();

        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        graphics.scale(1, -1);
        graphics.setBackground(Color.BLACK);

        for (int i = 0; i < vertices.size(); i++) {
            Vertex next = vertices.get((i + 1) % vertices.size());
            lines.add(new Line2D.Double(vertices.get(i).getX(), vertices.get(i).getY(), next.getX(), next.getY()));
        }

        if (lines.size() > 300) {
            for (int i = 0; i < 4; i++) {
                lines.remove(i);
            }
        }

        graphics.setColor(Color.getHSBColor(lineColor, 0.8f, 1));
        if (lineColor >= 1) lineColor = 0;
        else lineColor += 1 / 255f;

        for (Line2D l : lines) {
            graphics.draw(l);
        }
    }

    public void update(double deltaTime)
    {
        for (Vertex v : vertices) {
            double oldX = v.getX();
            double oldY = v.getY();

            if (oldX <= -960 || oldX >= 960) {
                v.setDirection(-v.getDirection());
            } else if (oldY <= -540 || oldY >= 540) {
                v.setRc(-(1 / v.getRc()));
            }

            if (v.getRc() > 0 && oldY > 540) {
                v.setRc(-(1 / v.getRc()));
            } else if (v.getRc() < 0 && oldY < -540) {
                v.setRc(-(1 / v.getRc()));
            }

            double x = oldX + (v.getDirection() * 5);
            double y = oldY + (5 * v.getRc());
            v.setLocation(new Point2D.Double(x, y));
        }
    }

    public static void main(String[] args)
    {
        launch(Screensaver.class);
    }

}
