package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    JuliaSetCanvas juliaSetCanvas = new JuliaSetCanvas(500, 500);
    JuliaSetParameters defaultParameters = new JuliaSetParameters(0,0,1,-0.576, -1, 500);
    CalculateSetController calculateSetController = new CalculateSetController(juliaSetCanvas, defaultParameters);
    ControlPanel controlPanel = new ControlPanel(juliaSetCanvas, calculateSetController);

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane borderPane = new BorderPane();

        JuliaSetCanvas juliaSetCanvas = new JuliaSetCanvas(500, 500);
        borderPane.setRight(juliaSetCanvas);

        ControlPanel controlPanel = new ControlPanel(juliaSetCanvas);
        borderPane.setLeft(controlPanel);



        Scene scene = new Scene(borderPane, 500, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
