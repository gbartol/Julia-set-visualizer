package com.pmf.juliasetvisualizer.controllers;

import com.pmf.juliasetvisualizer.calculators.JuliaSetCalculator;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import com.pmf.juliasetvisualizer.ui.ControlPanel;

import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.imaginaryTextField;
import static com.pmf.juliasetvisualizer.ui.ControlPanel.realTextField;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class CalculateSetController implements EventHandler<ActionEvent> {
    private double real;
    private double imaginary;
    private int maxIter;
    public JuliaSetCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;
    private JuliaSetParameters juliaSetParameters;
    long vrijeme;

    public CalculateSetController(JuliaSetCanvas canvas, JuliaSetParameters juliaSetParameters) {
        this.juliaSetParameters = juliaSetParameters;
        this.real = juliaSetParameters.getcReal();
        this.imaginary = juliaSetParameters.getcImaginary();
        this.maxIter = juliaSetParameters.getMaxIterations();
        this.canvas=canvas;
        canvasWidth = (int) canvas.getWidth();
        canvasHeight = (int) canvas.getHeight();
        if(canvas==null){
            System.out.println("canvas je null u Controlleru");
        }
        this.canvas.kontrolniint=4;
        System.out.println("kontrolniint je "+canvas.kontrolniint);


        this.canvas.setJuliaSetParameters(juliaSetParameters);
        this.canvas.setCalculateSetController(this);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if(!isValidInput()) return;

        calculate(juliaSetParameters);
    }

    public void calculate(JuliaSetParameters juliaSetParameters) {
        this.juliaSetParameters = juliaSetParameters;
        this.real = juliaSetParameters.getcReal();
        this.imaginary = juliaSetParameters.getcImaginary();
        this.maxIter = juliaSetParameters.getMaxIterations();

        new Thread(() -> {
            long startTime = System.currentTimeMillis();
       /*
        Thread thread1 = new Thread(new JuliaSetCalculator(canvas,1, real, imaginary));
        Thread thread2 = new Thread(new JuliaSetCalculator(canvas,2, real, imaginary));
        Thread thread3 = new Thread(new JuliaSetCalculator(canvas,3, real, imaginary));
        Thread thread4 = new Thread(new JuliaSetCalculator(canvas,4, real, imaginary));
        Ovako bi bilo problematično s koordinacijom. Lakše s Executorom
        */

            // U buffer će svaka dretva spremati rezultat. on će se ispisati tek nakon što sve dretve završe
            int[][] buffer = new int[canvasWidth][canvasHeight];

            //Upali 4 threada i daj im zadatke
            ExecutorService executor = Executors.newFixedThreadPool(4);

            executor.submit(new JuliaSetCalculator(canvas, 1, buffer, real, imaginary, maxIter, juliaSetParameters));
            executor.submit(new JuliaSetCalculator(canvas, 2, buffer, real, imaginary, maxIter, juliaSetParameters));
            executor.submit(new JuliaSetCalculator(canvas, 3, buffer, real, imaginary, maxIter, juliaSetParameters));
            executor.submit(new JuliaSetCalculator(canvas, 4, buffer, real, imaginary, maxIter, juliaSetParameters));

            //kill the executor
            executor.shutdown();
            try {
                executor.awaitTermination(60, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(buffer);

            Platform.runLater(() -> {
                GraphicsContext graphCont = canvas.getGraphicsContext2D();
                PixelWriter pixwrite = graphCont.getPixelWriter();
                //Time for crtanje
                for (int x = 0; x < canvasWidth; x++) {
                    for (int y = 0; y < canvasHeight; y++) {
                        int iteracija = buffer[x][y];
                        if (iteracija == maxIter)
                            pixwrite.setColor(x, y, Color.BLACK);
                        else
                            //hsb = hue, saturation, brightness. hue je 360*t za 0<=t<=1
                            pixwrite.setColor(x, y, Color.hsb(360 * ((double) iteracija / maxIter), 1.0, 1.0));
                    }
                }

                long endTime = System.currentTimeMillis();
                this.vrijeme=endTime-startTime;

            });

        }).start();
        System.out.println("Vrijeme je "+this.vrijeme);
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
