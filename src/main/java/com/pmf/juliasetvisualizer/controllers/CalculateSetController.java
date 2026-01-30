package com.pmf.juliasetvisualizer.controllers;

import com.pmf.juliasetvisualizer.calculators.JuliaSetCalculator;
import com.pmf.juliasetvisualizer.ui.CalculateSetButton;
import com.pmf.juliasetvisualizer.ui.ControlPanel;

import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.imaginaryTextField;
import static com.pmf.juliasetvisualizer.ui.ControlPanel.realTextField;

public class CalculateSetController implements EventHandler<ActionEvent> {
    private double real;
    private double imaginary;
    public static JuliaSetCanvas Canvas;

    public CalculateSetController(JuliaSetCanvas canvas) {
        this.real = Double.parseDouble(ControlPanel.realTextField.getText());
        this.imaginary = Double.parseDouble(imaginaryTextField.getText());
        System.out.println(this.real);
        System.out.println(this.imaginary);
        Canvas=canvas;
        if(Canvas==null){
            System.out.println("Canvas je null u Controlleru");
        }
        Canvas.kontrolniint=4;
        System.out.println("kontrolniint je "+Canvas.kontrolniint);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if(isValidInput()) {
            long startTime=System.currentTimeMillis();
            Thread thread1 = new Thread(new JuliaSetCalculator(Canvas,1, real, imaginary));
            Thread thread2 = new Thread(new JuliaSetCalculator(Canvas,2, real, imaginary));
            Thread thread3 = new Thread(new JuliaSetCalculator(Canvas,3, real, imaginary));
            Thread thread4 = new Thread(new JuliaSetCalculator(Canvas,4, real, imaginary));
            long endTime=System.currentTimeMillis();
            long vrijeme=startTime-endTime;
            System.out.println("Vrijeme je "+vrijeme);
        }
    }
    public static boolean isValidInput() {
        boolean valid = true;

        try {
            // Provjerava je li upisani broj tipa double, inace baca exception
            double real = Double.parseDouble(realTextField.getText());
            // ako je broj tipa double onda stavlja boju na default
            realTextField.setStyle("-fx-border-color: default");
        } catch (NumberFormatException e) {
            // inace stavlja boju na crvenu
            realTextField.setStyle("-fx-border-color: red");
            valid = false;
        }
        try {
            // ovo radi istu stvar samo s drugim text boxom
            double imaginary = Double.parseDouble(imaginaryTextField.getText());
            imaginaryTextField.setStyle("-fx-border-color: default");
        } catch (NumberFormatException e) {
            imaginaryTextField.setStyle("-fx-border-color: red");
            valid = false;
        }

        if(valid)
            return true;
        else
            return false;
    }
}
