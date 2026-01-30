package com.pmf.juliasetvisualizer.ui.ui;

import com.pmf.juliasetvisualizer.ui.controllers.CalculateSetController;
import javafx.scene.Node;
import javafx.scene.control.Button;

import static com.pmf.juliasetvisualizer.ui.ui.ControlPanel.imaginaryTextField;
import static com.pmf.juliasetvisualizer.ui.ui.ControlPanel.realTextField;

public class CalculateSetButton extends Button {
    public static JuliaSetCanvas Canvas;

// Konstruktori
    public CalculateSetButton(JuliaSetCanvas canvas) {
        super();
        Canvas=canvas;
        setAction(Canvas);
        setStyle();

    }

    public CalculateSetButton(JuliaSetCanvas canvas,String text) {
        super(text);
        Canvas=canvas;
        if(Canvas==null){
            System.out.println("Canvas je null u Buttonu");
        }
        setAction(Canvas);
        setStyle();


    }

    public CalculateSetButton(JuliaSetCanvas canvas,String text, Node graphic) {
        super(text, graphic);
        Canvas=canvas;
        if(Canvas==null){
            System.out.println("Canvas je null u Buttonu");
        }
        setAction(Canvas);
        setStyle();

    }

// Funkcija koja definira što će se dogoditi kad je gumb kliknut
    private void setAction(JuliaSetCanvas Canvas) {
        if(Canvas==null){
            System.out.println("Canvas je null u ButtonuActionu");
        }
        this.setOnAction(new CalculateSetController(Canvas));
    }

    private void setStyle() {
    }
}
