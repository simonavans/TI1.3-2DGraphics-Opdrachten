package test;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;

public class VerletDemo extends Application
{
    private ArrayList<Particle> particles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Canvas canvas = new Canvas(1920, 1080);
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        draw(graphics);

        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Hello Verlet");
        primaryStage.show();

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1e9);
                last = now;
                draw(graphics);
            }
        }.start();
    }

    private void update(double deltaTime)
    {
        for (Particle particle : particles) {
            particle.update();
        }
    }

    private void draw(FXGraphics2D graphics)
    {
        graphics.setColor(Color.WHITE);
        graphics.clearRect(0, 0, 1920, 1080);

        for (Particle particle : particles) {
            particle.draw(graphics);
        }
    }
}
