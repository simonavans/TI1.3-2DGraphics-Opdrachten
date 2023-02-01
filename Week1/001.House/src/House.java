import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        // Outline
        graphics.drawLine(100, 500, 400, 500);

        graphics.drawLine(100, 500, 100, 300);
        graphics.drawLine(400, 500, 400, 300);

        graphics.drawLine(100, 300, 250, 150);
        graphics.drawLine(400, 300, 250, 150);

        // Door
        graphics.drawLine(150, 500, 150, 400);
        graphics.drawLine(200, 500, 200, 400);

        graphics.drawLine(150, 400, 200, 400);

        // Window
        graphics.drawLine(250, 375, 375, 375);
        graphics.drawLine(250, 450, 375, 450);

        graphics.drawLine(250, 375, 250, 450);
        graphics.drawLine(375, 375, 375, 450);
    }



    public static void main(String[] args) {
        launch(House.class);
    }

}
