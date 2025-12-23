package com.pmf.juliasetvisualizer.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControlPanel extends VBox {
    public ControlPanel() {
        Slider maxIterationsSlider = new Slider(500, 10000, 1000);
        //maxIterationsSlider.setPadding(new Insets(50,50,50,50));

        Text maxIterationsText = new Text();
        maxIterationsText.textProperty().bind(
                maxIterationsSlider.valueProperty().asString("N = %.0f")
        );

        super(maxIterationsSlider, maxIterationsText);
    }
}
