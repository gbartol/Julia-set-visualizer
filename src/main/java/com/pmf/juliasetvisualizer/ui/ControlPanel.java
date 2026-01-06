package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.models.CalculateSetButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControlPanel extends VBox {

    public static Slider maxIterationsSlider;
    public static TextField realTextField;
    public static TextField imaginaryTextField;

    public ControlPanel() {
        super(10);
        setPadding(new Insets(10));
        setMinWidth(250);

        initializeComponents();
    }

    private void initializeComponents() {
    // Label za slider
        Label maxIterationsLabel = new Label("Maksimalan broj iteracija:");
        maxIterationsLabel.setStyle("-fx-font-weight: bold;");
    // Slider
        maxIterationsSlider = new Slider(500, 10000, 1000);
        //maxIterationsSlider.setPadding(new Insets(50,50,50,50));
    // Tekst koji prati slider (N=500)
        Text maxIterationsText = new Text();
        maxIterationsText.textProperty().bind(
                maxIterationsSlider.valueProperty().asString("N = %.0f") //ovo sluzi da broj u tekstu prati slider
        );

        VBox maxIterations = new VBox(maxIterationsSlider, maxIterationsText);

    // Text boxovi za unos konstante c
        realTextField = new TextField("1.2");
        imaginaryTextField = new TextField("1.2");

        VBox constantTextField = new VBox(realTextField, imaginaryTextField);

        getChildren().addAll(
            maxIterations,
            new Separator(),
            constantTextField,
            new CalculateSetButton()
        );
    }
}
