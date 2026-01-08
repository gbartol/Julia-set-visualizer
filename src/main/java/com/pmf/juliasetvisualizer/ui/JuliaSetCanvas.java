package com.pmf.juliasetvisualizer.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaSetCanvas extends Canvas {
    public GraphicsContext gc;
    public PixelWriter pw;
// Konstruktori
    public JuliaSetCanvas() {
        super();
        gc = this.getGraphicsContext2D();
        pw = gc.getPixelWriter();
    }
    public JuliaSetCanvas(double width, double height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        pw = gc.getPixelWriter();
    }
}