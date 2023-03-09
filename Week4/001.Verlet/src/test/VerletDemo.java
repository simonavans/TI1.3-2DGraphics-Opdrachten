package test;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VerletDemo extends Application
{
    private int canvasWidth = 1920;
    private int canvasHeight = 1080;
    private ArrayList<Particle> particles = new ArrayList<>();
    private ArrayList<Constraint> constraints = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

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

        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Hello Verlet");
        primaryStage.show();

        draw(graphics);
    }

    public void init()
    {
        particles.add(new Particle(new Point2D.Double(1420/2d, 400), canvasWidth, canvasHeight));
        particles.add(new Particle(new Point2D.Double(1920/2d, 0), canvasWidth, canvasHeight));
        constraints.add(new DistanceConstraint(particles.get(0), particles.get(1)));
        constraints.add(new StaticConstraint(particles.get(0)));
    }

    private void update(double deltaTime)
    {
        for (Particle particle : particles)
        {
            particle.update();
        }

        for (Constraint constraint : constraints)
        {
            constraint.satisfy();
        }
    }

    private void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, canvasWidth, canvasHeight);

        for (Particle particle : particles)
        {
            particle.draw(graphics);
        }

        for (Constraint constraint : constraints)
        {
            constraint.draw(graphics);
        }
    }
}
