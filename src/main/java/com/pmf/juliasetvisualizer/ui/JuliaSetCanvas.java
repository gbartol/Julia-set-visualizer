package com.pmf.juliasetvisualizer.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaSetCanvas extends Canvas {
    public GraphicsContext gc;
    public PixelWriter pw;
    public int kontrolniint=0;
// Konstruktori
    public JuliaSetCanvas() {
        super();
        kontrolniint=1;
        System.out.println("kontrolniint je "+kontrolniint);
        gc = this.getGraphicsContext2D();
        pw = gc.getPixelWriter();
    }
    public JuliaSetCanvas(double width, double height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        pw = gc.getPixelWriter();
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                pw.setColor(i,j,Color.RED);
            }
        }


    }
    public JuliaSetCanvas getCanvas(){
        return this;
    }
}