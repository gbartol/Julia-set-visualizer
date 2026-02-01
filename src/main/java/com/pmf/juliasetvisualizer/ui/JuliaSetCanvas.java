package com.pmf.juliasetvisualizer.ui;

import com.pmf.juliasetvisualizer.controllers.CalculateSetController;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class JuliaSetCanvas extends Canvas {
    public GraphicsContext gc;
    public PixelWriter pw;
    public int kontrolniint=0;

    private JuliaSetParameters juliaSetParameters;
    private CalculateSetController calculateSetController;
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
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                pw.setColor(i,j,Color.RED);
            }
        }

        setOnMouseClicked(event -> {
            if (juliaSetParameters == null) return;

            double clickedReal = juliaSetParameters.mapPixelToReal((int) event.getX(), (int) getWidth());
            double clickedImag = juliaSetParameters.mapPixelToImaginary((int) event.getY(), (int) getHeight());

            double zoomFactor = event.getButton() == MouseButton.PRIMARY ? 2.0 : 0.5;

            JuliaSetParameters newParameters = new JuliaSetParameters(
                    clickedReal,
                    clickedImag,
                    juliaSetParameters.getZoom() * zoomFactor,
                    juliaSetParameters.getcReal(),
                    juliaSetParameters.getcImaginary(),
                    juliaSetParameters.getMaxIterations()
            );

            this.juliaSetParameters = newParameters;
            calculateSetController.calculate(newParameters);
        });
    }

    public JuliaSetCanvas getCanvas(){
        return this;
    }

    public void setJuliaSetParameters(JuliaSetParameters juliaSetParameters) {
        this.juliaSetParameters = juliaSetParameters;
    }
    public void setCalculateSetController(CalculateSetController calculateSetController) {
        this.calculateSetController = calculateSetController;
    }
}