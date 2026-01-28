package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import javafx.scene.Node;
import javafx.scene.control.Button;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.imaginaryTextField;
import static com.pmf.juliasetvisualizer.ui.ControlPanel.realTextField;

public class CalculateSetButton extends Button {

// Konstruktori
    public CalculateSetButton() {
        super();
        setAction();
        setStyle();
    }

    public CalculateSetButton(String text) {
        super(text);
        setAction();
        setStyle();
    }

    public CalculateSetButton(String text, Node graphic) {
        super(text, graphic);
        setAction();
        setStyle();
    }

// Funkcija koja definira što će se dogoditi kad je gumb kliknut
    private void setAction() {
        this.setOnAction(new CalculateSetController());
    }

    private void setStyle() {
    }
}
