import java.awt.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private TextField red;
    private TextField green;
    private TextField blue;
    private ArrayList<SpirographPattern> spirographPatterns = new ArrayList<>();
    private SpirographPattern activePattern;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);

        Button addButton = new Button("Add");
        Button clearButton = new Button("Clear");
       
        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        topBar.setSpacing(10);
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));

        topBar.getChildren().add(new Label("a-value:"));
        topBar.getChildren().add(v1 = new TextField("300"));
        topBar.getChildren().add(new Label("b-value:"));
        topBar.getChildren().add(v2 = new TextField("1"));
        topBar.getChildren().add(new Label("c-value:"));
        topBar.getChildren().add(v3 = new TextField("300"));
        topBar.getChildren().add(new Label("d-value:"));
        topBar.getChildren().add(v4 = new TextField("10"));
        topBar.getChildren().add(new Label("Red:"));
        topBar.getChildren().add(red = new TextField("255"));
        topBar.getChildren().add(new Label("Green:"));
        topBar.getChildren().add(green = new TextField("200"));
        topBar.getChildren().add(new Label("Blue:"));
        topBar.getChildren().add(blue = new TextField("0"));
        topBar.getChildren().add(addButton);
        topBar.getChildren().add(clearButton);

        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);
                
        v1.textProperty().addListener(e -> draw(graphics));
        v2.textProperty().addListener(e -> draw(graphics));
        v3.textProperty().addListener(e -> draw(graphics));
        v4.textProperty().addListener(e -> draw(graphics));
        red.textProperty().addListener(e -> draw(graphics));
        green.textProperty().addListener(e -> draw(graphics));
        blue.textProperty().addListener(e -> draw(graphics));

        draw(graphics);

        addButton.setOnAction(e -> spirographPatterns.add(activePattern));
        clearButton.setOnAction(e -> {
            spirographPatterns.clear();
            graphics.setColor(Color.white);
            graphics.fillRect(-4000, -4000, 8000, 8000);
            draw(graphics);
        });

        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        double a = !v1.getText().isEmpty() ? Double.parseDouble(v1.getText()) : 0;
        double b = !v2.getText().isEmpty() ? Double.parseDouble(v2.getText()) : 0;
        double c = !v3.getText().isEmpty() ? Double.parseDouble(v3.getText()) : 0;
        double d = !v4.getText().isEmpty() ? Double.parseDouble(v4.getText()) : 0;

        int redCol = !red.getText().isEmpty() ? Integer.parseInt(red.getText()) : 0;
        int greenCol = !green.getText().isEmpty() ? Integer.parseInt(green.getText()) : 0;
        int blueCol = !blue.getText().isEmpty() ? Integer.parseInt(blue.getText()) : 0;

        graphics.setColor(Color.white);
        graphics.fillRect(-4000, -4000, 8000, 8000);

        for (SpirographPattern pattern : spirographPatterns) {
            pattern.draw();
        }

        SpirographPattern pattern = new SpirographPattern(graphics, a, b, c, d, redCol, greenCol, blueCol);
        pattern.draw();
        activePattern = pattern;
    }

    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}
