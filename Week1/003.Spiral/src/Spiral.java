import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        graphics.setColor(Color.red);
        graphics.drawLine(0,0,1000,0);
        graphics.setColor(Color.green);
        graphics.drawLine(0,0,0,1000);
        graphics.setColor(Color.black);

        double resolution = 0.1;
        double scale = 50.0;
        double lastX = 0;
        double lastY = 0;

        for(double theta = 0; theta < 10; theta += resolution)
        {
            float x = (float) (theta * Math.cos(theta));
            float y = (float) (theta * Math.sin(theta));

            graphics.draw(new Line2D.Double(x*scale, y*scale, lastX*scale, lastY*scale));
            lastX = x;
            lastY = y;
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
