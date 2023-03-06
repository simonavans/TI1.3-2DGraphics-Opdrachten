import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class FadingImage extends Application implements GraphicsEngine
{
    private ResizableCanvas canvas;
    private boolean imageToggle;
    private BufferedImage image0;
    private BufferedImage image1;
    private int framesElapsed;
    private boolean isFadingOut;
    private boolean isFadingIn;
    private float blend;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);

        this.setup();

        stage.setScene(new Scene(mainPane, 800, 600));
        stage.setTitle("Fading image");
        stage.show();
    }

    @Override
    public void setup()
    {
        try
        {
            this.image0 = ImageIO.read(getClass().getResource("/pic0.jpg"));
            this.image1 = ImageIO.read(getClass().getResource("/pic1.jpg"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        this.imageToggle = true;
        new AccumulationTimer(60, this, new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    @Override
    public void update()
    {
        if (!isFadingOut && framesElapsed == 120)
        {
            framesElapsed = 0;
            isFadingOut = true;
            blend = 1f;
        }
        else if (isFadingOut && blend > 0)
        {
            blend -= 0.01;
        }
        else if (isFadingOut)
        {
            isFadingOut = false;
            isFadingIn = true;
            this.imageToggle = !this.imageToggle;
        }
        else if (isFadingIn && blend < 1)
        {
            blend += 0.01;
        }
        else if (isFadingIn)
        {
            isFadingIn = false;
        }
        else
        {
            this.framesElapsed++;
        }
    }

    @Override
    public void draw(FXGraphics2D graphics)
    {
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, blend >= 0 ? blend : 0));

        if (imageToggle)
        {
            graphics.drawImage(image0, 0, 0, null);
        }
        else
        {
            graphics.drawImage(image1, 0, 0, null);
        }
    }

    public static void main(String[] args)
    {
        launch(FadingImage.class);
    }
}
