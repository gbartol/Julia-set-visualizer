package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControlPanel extends VBox {

    private static Slider maxIterationsSlider;
    public static TextField realTextField;
    public static TextField imaginaryTextField;
    private static Text setDefinitionText;
    public static CalculateSetButton calculateSetButton;
    public JuliaSetCanvas canvas;

    public ControlPanel(JuliaSetCanvas canvas) {
        super(10);
        setPadding(new Insets(10));
        setMinWidth(250);
        this.canvas=canvas;
        if(this.canvas==null){
            System.out.println("canvas je null u ControllPanelu");
        }
        this.canvas.kontrolniint=2;
        System.out.println("kontrolniint je "+this.canvas.kontrolniint);
        initializeComponents(canvas);
    }

    private void initializeComponents(JuliaSetCanvas canvas) {
    // Label za slider
        Label maxIterationsLabel = new Label("Maksimalan broj iteracija:");
        maxIterationsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
    // Slider
        maxIterationsSlider = new Slider(100, 1000, 100);
        //maxIterationsSlider.setPadding(new Insets(50,50,50,50));
    // Tekst koji prati slider (N=500)
        Text maxIterationsText = new Text();
        maxIterationsText.textProperty().bind(
                maxIterationsSlider.valueProperty().asString("N = %.0f") //ovo sluzi da broj u tekstu prati slider
        );
        maxIterationsText.setStyle("-fx-font-size: 18px;");

        VBox maxIterations = new VBox(maxIterationsLabel, maxIterationsSlider, maxIterationsText);

    // Label za upis konstante
        Label constantLabel = new Label("Konstanta:");
        constantLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

    // Text boxovi za unos konstante c
        realTextField = new TextField("-0.8");
        imaginaryTextField = new TextField("0.156");

    // Button
        calculateSetButton = new CalculateSetButton(canvas,"Calculate Julia set!");

    // Tekst koji ispisuje definiciju skupa
        setDefinitionText = new Text("Z\u2099\u208A\u2081 = Z\u2099² + " + realTextField.getText() + " + " + imaginaryTextField.getText() + "i");
        setDefinitionText.setStyle("-fx-font-size: 18px;");

        // listeneri za upis teksta u text box, pozivaju funkciju dole
        realTextField.textProperty().addListener(observable -> updateSetDefinitionText());
        imaginaryTextField.textProperty().addListener(observable -> updateSetDefinitionText());

        VBox constantTextField = new VBox(constantLabel, realTextField, imaginaryTextField, setDefinitionText);

        getChildren().addAll(
            maxIterations,
            new Separator(),
            constantTextField,
            calculateSetButton,
            canvas
        );
    }

    private void updateSetDefinitionText() {
        // Provjerava je li input u tekst boxovima dobar
        if(CalculateSetController.isValidInput()) {
            // ako je updatea tekst

            String signReal = (Double.parseDouble(realTextField.getText()) > 0) ? "+" : "";
            String signImaginary = (Double.parseDouble(imaginaryTextField.getText()) > 0) ? "+" : "";

            setDefinitionText.setText("Z\u2099\u208A\u2081 = Z\u2099² " + signReal + realTextField.getText() + signImaginary + imaginaryTextField.getText() + "i");
        }
    }
    
    public static Slider getMaxIterationsSlider(){
        return maxIterationsSlider;
    }
}
