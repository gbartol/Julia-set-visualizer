package com.pmf.juliasetvisualizer.controllers;

import com.pmf.juliasetvisualizer.calculators.JuliaSetCalculator;
import com.pmf.juliasetvisualizer.ui.CalculateSetButton;
import com.pmf.juliasetvisualizer.ui.ControlPanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.imaginaryTextField;
import static com.pmf.juliasetvisualizer.ui.ControlPanel.realTextField;

public class CalculateSetController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        if(isValidInput()) {
            new JuliaSetCalculator();
        }
    }
    private boolean isValidInput() {
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
