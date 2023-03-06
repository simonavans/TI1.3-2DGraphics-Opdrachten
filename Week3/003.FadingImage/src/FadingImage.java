import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class FadingImage extends Application
{
    private ResizableCanvas canvas;
    private boolean imageToggle;
    private BufferedImage image0;
    private BufferedImage image1;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        image0 = ImageIO.read(getClass().getResource("/pic0.jpg"));
        image1 = ImageIO.read(getClass().getResource("/pic1.jpg"));
        imageToggle = true;

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now)
            {
                if(last == -1)
                    last = now;
                if (now - last > 5e9)
                {
                    update(1);
                    last = now;
                    draw(g2d);
                }
            }
        }.start();
        stage.setScene(new Scene(mainPane, 800, 600));
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }
    
    
    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());

        if (imageToggle)
        {
            graphics.drawImage(image0, 0, 0, null);
        }
        else
        {
            graphics.drawImage(image1, 0, 0, null);
        }
    }


    public void update(double deltaTime)
    {
        imageToggle = !imageToggle;
    }

    public static void main(String[] args) {launch(FadingImage.class);}
}
