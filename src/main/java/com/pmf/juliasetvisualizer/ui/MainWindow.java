package com.pmf.juliasetvisualizer.ui;

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
