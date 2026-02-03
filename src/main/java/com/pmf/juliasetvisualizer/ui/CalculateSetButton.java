package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import javafx.scene.Node;
import javafx.scene.control.Button;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.*;

public class CalculateSetButton extends Button {
    public JuliaSetCanvas canvas;
    private CalculateSetController calculateSetController;

// Konstruktori
    public CalculateSetButton(JuliaSetCanvas canvas) {
        super();
        this.canvas=canvas;
        setAction(this.canvas);
        setStyle();

    }

    public CalculateSetButton(JuliaSetCanvas canvas, CalculateSetController calculateSetController, String text) {
        super(text);
        this.canvas=canvas;
        this.calculateSetController = calculateSetController;
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
        this.setOnAction(e -> {
            int maxIter = (int) ControlPanel.getMaxIterationsSlider().getValue();
            JuliaSetParameters newParameters = new JuliaSetParameters(
                    0.0, 0.0, 1.0,
                    Double.parseDouble(ControlPanel.realTextField.getText()), Double.parseDouble(ControlPanel.imaginaryTextField.getText()),
                    maxIter,ControlPanel.getColorSlider().getValue());
            canvas.setJuliaSetParameters(newParameters);
            calculateSetController.calculate(newParameters);
        });
    }

    private void setStyle() {
    }
}
