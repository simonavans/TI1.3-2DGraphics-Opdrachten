import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class MovingCharacter extends Application
{
    private Canvas canvas;
    private BufferedImage[] tiles;
    private int currentTile = 0;

    @Override
    public void start(Stage stage) throws Exception
    {
        canvas = new Canvas(1920, 1080);

        try
        {
            int tileEdgeLength = 64;
            BufferedImage image = ImageIO.read(getClass().getResource("/images/sprite.png"));
            tiles = new BufferedImage[16];
            for (int i = 0; i < tiles.length; i++)
            {
                tiles[i] = image.getSubimage(tileEdgeLength * (i % 6), tileEdgeLength * 4, tileEdgeLength, tileEdgeLength);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        new AnimationTimer()
        {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;

                if (now - last > 5e7)
                {
                    last = now;
                    draw(graphics);
                }
            }
        }.start();

        stage.setScene(new Scene(new Group(canvas)));
        stage.setTitle("Moving Character");
        stage.show();
        draw(graphics);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform transform = new AffineTransform();
        transform.translate(400, 400);
        transform.scale(2, 2);
        graphics.drawImage(tiles[currentTile], transform, null);
        currentTile = (currentTile + 1) % tiles.length;
    }

    public static void main(String[] args)
    {
        launch(MovingCharacter.class);
    }

}
