package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import javafx.event.ActionEvent;
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
    public static Text setDefinitionText;
    public static CalculateSetButton calculateSetButton;

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

        VBox maxIterations = new VBox(maxIterationsLabel, maxIterationsSlider, maxIterationsText);

    // Label za upis konstante
        Label constantLabel = new Label("Konstanta:");
        constantLabel.setStyle("-fx-font-weight: bold;");

    // Text boxovi za unos konstante c
        realTextField = new TextField("1.2");
        imaginaryTextField = new TextField("1.2");

    // Button
        calculateSetButton = new CalculateSetButton("Calculate Julia set!");

    // Tekst koji ispisuje definiciju skupa
        setDefinitionText = new Text("Zn+1 = Zn^2 + " + realTextField.getText() + " + " + imaginaryTextField.getText() + "i");
        // listeneri za upis teksta u text box, pozivaju funkciju dole
        realTextField.textProperty().addListener(observable -> updateSetDefinitionText());
        imaginaryTextField.textProperty().addListener(observable -> updateSetDefinitionText());

        VBox constantTextField = new VBox(constantLabel, realTextField, imaginaryTextField, setDefinitionText);

        getChildren().addAll(
            maxIterations,
            new Separator(),
            constantTextField,
            calculateSetButton
        );
    }

    private void updateSetDefinitionText() {
        // Provjerava je li input u tekst boxovima dobar
        if(CalculateSetController.isValidInput()) {
            // ako je updatea tekst
            setDefinitionText.setText("Zn+1 = Zn^2 + " + realTextField.getText() + " + " + imaginaryTextField.getText() + "i");
        }
    }
}
