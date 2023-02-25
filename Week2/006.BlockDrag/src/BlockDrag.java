import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class BlockDrag extends Application {
    private ResizableCanvas canvas;
    private Point2D startPan = new Point2D.Double();
    private double zoomFactor = 1.05;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.resize(960, 540);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();
        canvas.requestFocus();

        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        graphics.setTransform(new AffineTransform());

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e, graphics));
        canvas.setOnScroll(e -> scroll(e, graphics));

        draw(graphics);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        graphics.fillRect(300, 300, 50, 50);
    }


    public static void main(String[] args)
    {
        launch(BlockDrag.class);
    }

    private void mousePressed(MouseEvent e)
    {
        startPan.setLocation(e.getX(), e.getY());
    }

    private void mouseReleased(MouseEvent e)
    {

    }

    private void mouseDragged(MouseEvent e, FXGraphics2D graphics)
    {
        double diffX = e.getX() - startPan.getX();
        double diffY = e.getY() - startPan.getY();

        startPan.setLocation(e.getX(), e.getY());
        graphics.translate(diffX / zoomFactor, diffY / zoomFactor);
        draw(graphics);
    }

    private void scroll(ScrollEvent e, FXGraphics2D graphics) {
        Point2D beforeScroll = new Point2D.Double(e.getX(), e.getY());
        double localZoomFactor = e.getDeltaY() < 0 ? 0.95 : zoomFactor;

        graphics.scale(canvas.getScaleX() * localZoomFactor, canvas.getScaleY() * localZoomFactor);
        draw(graphics);

        Point2D diffScroll = new Point2D.Double(e.getX() - beforeScroll.getX(), e.getY() - beforeScroll.getY());
        graphics.translate(diffScroll.getX(), diffScroll.getY());
        draw(graphics);
    }

}
