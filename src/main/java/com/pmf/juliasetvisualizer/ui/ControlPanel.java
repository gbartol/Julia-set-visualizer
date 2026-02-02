package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import com.pmf.juliasetvisualizer.db.JuliaSetDAO;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;




public class ControlPanel extends VBox {

    private static Slider maxIterationsSlider;
    public static TextField realTextField;
    public static TextField imaginaryTextField;
    private static Text setDefinitionText;
    public static CalculateSetButton calculateSetButton;
    public JuliaSetCanvas canvas;
    private CalculateSetController calculateSetController;
    private ListView<JuliaSetParameters> savedSetsListView;

    public ControlPanel(JuliaSetCanvas canvas, CalculateSetController calculateSetController) {
        super(10);
        setPadding(new Insets(10));
        setMinWidth(250);
        this.calculateSetController = calculateSetController;
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
        maxIterationsSlider = new Slider(100, 10000, 100);
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

    // Button za izračun
        calculateSetButton = new CalculateSetButton(canvas, calculateSetController, "Calculate Julia set!");
        
        Button saveButton = new Button("Spremi Julia Set");
        saveButton.setStyle("-fx-font-size: 14px;");
        
        saveButton.setOnAction(e -> {
            
            
            if(canvas.getJuliaSetParameters() != null)
            {
                JuliaSetParameters parameters = canvas.getJuliaSetParameters();
               long renderTime = calculateSetController.getRenderTime();
            
                JuliaSetDAO.save(parameters, renderTime);
                System.out.println("Novi set je spremljen u bazu");
             
            }
            else
            {
                System.out.println("Nema parametara");
            }
            
        });
        
        // lista spremljenih julia setova i zoomova
        ObservableList<JuliaSetParameters> savedSets = FXCollections.observableArrayList(JuliaSetDAO.selectAll());
        
        savedSetsListView = new ListView<>(savedSets);
        savedSetsListView.setPrefHeight(500);
        savedSetsListView.setStyle("fx-font-size: 14px;");
        
        savedSetsListView.setOnMouseClicked(e -> {
            JuliaSetParameters selected = savedSetsListView.getSelectionModel().getSelectedItem();
            if(selected != null)
            {
                canvas.setJuliaSetParameters(selected);
                calculateSetController.calculate(selected);
            }
        });
        
        //button refresha listu
        saveButton.setOnAction(e->{
            JuliaSetParameters parameters = canvas.getJuliaSetParameters();
            if(parameters != null)
            {
                JuliaSetDAO.save(parameters, calculateSetController.getRenderTime());
                System.out.println("Julia set spremljen u bazu!");
                
                savedSets.setAll(JuliaSetDAO.selectAll());
            }
        });
        
       

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
            new Separator(),
            saveButton,
            savedSetsListView
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
