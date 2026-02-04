package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import com.pmf.juliasetvisualizer.db.JuliaSetDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;




public class ControlPanel extends VBox {

    private static Slider maxIterationsSlider;
    private static Slider colorSlider;
    public static TextField realTextField;
    public static TextField imaginaryTextField;
    private static Text setDefinitionText;
    public static CalculateSetButton calculateSetButton;
    public JuliaSetCanvas canvas;
    private CalculateSetController calculateSetController;
    private ListView<JuliaSetParameters> savedSetsListView;
    private ObservableList<JuliaSetParameters> savedSets;
    private static Label renderTimeLabel;
    private ProgressBar progressBar;

    public ControlPanel(JuliaSetCanvas canvas, CalculateSetController calculateSetController, ProgressBar progressBar) {
        super(10);
        setPadding(new Insets(10));
        setMinWidth(250);
        this.calculateSetController = calculateSetController;
        this.progressBar = progressBar;
        this.calculateSetController.setProgressBar(progressBar);
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
    //Label za odabir boja
        Label colorsLabel = new Label("Boje:");
        colorsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        colorSlider= new Slider(0,1,100);
        Text colorText = new Text();
        colorText.textProperty().bind(
                colorSlider.valueProperty().asString("%.2f") //ovo sluzi da broj u tekstu prati slider
        );

        colorText.setStyle("-fx-font-size: 18px;");
        VBox colors = new VBox(colorsLabel,colorSlider,colorText);

    // Label za upis konstante
        Label constantLabel = new Label("Konstanta:");
        constantLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

    // Text boxovi za unos konstante c
        realTextField = new TextField("-0.8");
        imaginaryTextField = new TextField("0.156");

    // Button za izračun
        calculateSetButton = new CalculateSetButton(canvas, calculateSetController, "Calculate Julia set!");
        
    //Button za spremanje u bazu
        Button saveButton = new Button("Save");
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
        
        //Button za export
        
        Button exportBtn = new Button("Export as Image");
        
        exportBtn.setOnAction((ActionEvent e) -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Spremi sliku Julia seta");
                
                //Gledaj samo slike
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG slika", "*.png"),
                    new FileChooser.ExtensionFilter("JPG slika", "*.jpg")
                );
                
                fileChooser.setInitialFileName("julia_set_export.png");
                
                //centriranje
                File file = fileChooser.showSaveDialog(this.getScene().getWindow()); 
                
                if(file != null){
                    try{
                    WritableImage image = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight()); 
                    canvas.snapshot(null, image);
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

                    String fileName = file.getName().toLowerCase();
                    String format = (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) ? "jpg" : "png";

                    boolean success = ImageIO.write(bufferedImage, format, file);

                    if(success)
                        System.out.println("uspješno exportano u: " + file.getAbsolutePath());   
                
                   
                    }
                    catch (IOException ex){
                        ex.printStackTrace();
                        }
                    
                }
           
        });
        
        
        // lista spremljenih julia setova i zoomova
        savedSets = FXCollections.observableArrayList(JuliaSetDAO.selectAll());
        
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
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size: 14px;");
        
        deleteButton.setOnAction(e -> {
            
        JuliaSetParameters selected = savedSetsListView.getSelectionModel().getSelectedItem();
        
        if(selected == null){
            System.out.println("Nema odabira");
            return;
        }
        
        JuliaSetDAO.delete(selected.getId());
        savedSets.setAll(JuliaSetDAO.selectAll());
        });

        // Label za vrijeme izvrsavanja
       renderTimeLabel = new Label("");

    // Tekst koji ispisuje definiciju skupa
        setDefinitionText = new Text("Z\u2099\u208A\u2081 = Z\u2099² + " + realTextField.getText() + " + " + imaginaryTextField.getText() + "i");
        setDefinitionText.setStyle("-fx-font-size: 18px;");

        // listeneri za upis teksta u text box, pozivaju funkciju dole
        realTextField.textProperty().addListener(observable -> updateSetDefinitionText());
        imaginaryTextField.textProperty().addListener(observable -> updateSetDefinitionText());

        VBox constantTextField = new VBox(constantLabel, realTextField, imaginaryTextField, setDefinitionText);

        getChildren().addAll(
            maxIterations,
            colors,
            new Separator(),
            constantTextField,
            calculateSetButton,
            new Separator(),
            savedSetsListView,
            new HBox(deleteButton, saveButton),
            new Separator(),
            renderTimeLabel,
            progressBar,
            new Separator(),
            exportBtn
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
    public static Slider getColorSlider(){return colorSlider;}

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public static Label getRenderTimeLabel() {
        return renderTimeLabel;
    }
}
