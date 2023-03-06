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
    private FXGraphics2D graphics;
    private int x = -600;
    private int y = 600;

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(this::draw, mainPane);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Rainbow");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();

        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        draw(graphics);
        canvas.setOnMouseClicked(this::clicked);
    }

    private void clicked(MouseEvent e)
    {
        x += 10;
        draw(graphics);
    }

    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        int angle = -90;
        String text = "regenboog";
        Font font = new Font("Arial", Font.PLAIN, 150);
        FontRenderContext frc = graphics.getFontRenderContext();

        for (char ch : text.toCharArray())
        {
            Shape chShape = font.createGlyphVector(frc, String.valueOf(ch)).getOutline();
//            Shape chShape = new Rectangle2D.Double(0, 0, 10, 10);
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle));
            System.out.println(ch);
            transform.translate(x, y);

            graphics.draw(transform.createTransformedShape(chShape));
            angle += 30;
//            x += 50;
//            y += 30;
        }

//        graphics.draw(AffineTransform.getTranslateInstance(100, 100).createTransformedShape(shape));
    }

    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }
}
