import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Rainbow extends Application
{
    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Rainbow");
        stage.show();

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);

        String text = "regenboog";
        double angle = -(Math.PI / 2) + (Math.PI / text.length() / 4);
        Font font = new Font("Arial", Font.PLAIN, 150);
        FontRenderContext frc = graphics.getFontRenderContext();

        for (char ch : text.toCharArray())
        {
            Shape chShape = font.createGlyphVector(frc, String.valueOf(ch)).getOutline();
            AffineTransform transform = new AffineTransform();
            transform.translate(
                    250 * Math.cos(angle - (Math.PI / 2)),
                    250 * Math.sin(angle - (Math.PI / 2))
            );
            transform.rotate(angle);

            graphics.setColor(Color.getHSBColor((float) text.indexOf(ch) / text.length(), 1, 1));
            graphics.fill(transform.createTransformedShape(chShape));
            angle += Math.PI/text.length();
        }

//        graphics.draw(AffineTransform.getTranslateInstance(100, 100).createTransformedShape(shape));
    }

    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }
}
