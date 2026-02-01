package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import javafx.scene.Node;
import javafx.scene.control.Button;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.*;

public class CalculateSetButton extends Button {
    public JuliaSetCanvas canvas;

// Konstruktori
    public CalculateSetButton(JuliaSetCanvas canvas) {
        super();
        this.canvas=canvas;
        setAction(this.canvas);
        setStyle();

    }

    public CalculateSetButton(JuliaSetCanvas canvas,String text) {
        super(text);
        this.canvas=canvas;
        if(this.canvas==null){
            System.out.println("canvas je null u Buttonu");
        }
        setAction(this.canvas);
        setStyle();


    }

    public CalculateSetButton(JuliaSetCanvas canvas,String text, Node graphic) {
        super(text, graphic);
        this.canvas=canvas;
        if(this.canvas==null){
            System.out.println("canvas je null u Buttonu");
        }
        setAction(this.canvas);
        setStyle();

    }

// Funkcija koja definira što će se dogoditi kad je gumb kliknut
    private void setAction(JuliaSetCanvas Canvas) {
        if(Canvas==null){
            System.out.println("canvas je null u ButtonuActionu");
        }
        int maxIter = (int) ControlPanel.getMaxIterationsSlider().getValue();
        this.setOnAction(new CalculateSetController(canvas, new JuliaSetParameters(
                0.0, 0.0, 1.0,
                Double.parseDouble(ControlPanel.realTextField.getText()), Double.parseDouble(ControlPanel.imaginaryTextField.getText()),
                maxIter)));
    }

    private void setStyle() {
    }
}
