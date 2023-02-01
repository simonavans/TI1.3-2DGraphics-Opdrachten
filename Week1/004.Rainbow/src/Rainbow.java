import java.awt.*;
import java.awt.geom.*;
import java.util.stream.IntStream;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {
  private Canvas canvas;
  @Override
  public void start(Stage primaryStage) throws Exception {
    canvas = new Canvas(1920, 1080);
    draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    primaryStage.setScene(new Scene(new Group(canvas)));
    primaryStage.setTitle("Rainbow");
    primaryStage.show();
  }


  public void draw(FXGraphics2D graphics) {
    graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
    graphics.scale( 1, -1);

    int lowerScale = 200;
    int upperScale = 400;
    double lastX = 0;
    double lastY = 0;

    for (float i = 0; i < Math.PI; i+=0.001f) {
      graphics.setColor(Color.getHSBColor((float) (i/Math.PI), 1, 1));
      graphics.draw(new Line2D.Double(
              Math.cos(i)*lowerScale,
              Math.sin(i)*lowerScale,
              lastX*upperScale,
              lastY*upperScale));

      lastX = Math.cos(i);
      lastY = Math.sin(i);
    }
  }

  public static void main(String[] args) {
    launch(Rainbow.class);
  }

}
