package com.pmf.juliasetvisualizer.controllers;

import com.pmf.juliasetvisualizer.calculators.JuliaSetCalculator;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import com.pmf.juliasetvisualizer.ui.ControlPanel;

import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static com.pmf.juliasetvisualizer.ui.ControlPanel.imaginaryTextField;
import static com.pmf.juliasetvisualizer.ui.ControlPanel.realTextField;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class CalculateSetController implements EventHandler<ActionEvent> {
    private double real;
    private double imaginary;
    private int maxIter;
    public JuliaSetCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;
    private int totalPixels;
    private JuliaSetParameters juliaSetParameters;
    private long vrijeme;
    private int[][] buffer;
    private ProgressBar progressBar;
    private double colorValue;

    public CalculateSetController(JuliaSetCanvas canvas, JuliaSetParameters juliaSetParameters) {
        this.juliaSetParameters = juliaSetParameters;
        this.real = juliaSetParameters.getcReal();
        this.imaginary = juliaSetParameters.getcImaginary();
        this.maxIter = juliaSetParameters.getMaxIterations();
        this.colorValue=juliaSetParameters.getColorValue();
        this.canvas=canvas;
        this.canvasWidth = (int) canvas.getWidth();
        this.canvasHeight = (int) canvas.getHeight();
        this.totalPixels = canvasWidth * canvasHeight;
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
        this.colorValue=juliaSetParameters.getColorValue();

        Task calculateTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                long startTime = System.currentTimeMillis();

                // U buffer će svaka dretva spremati rezultat. on će se ispisati tek nakon što sve dretve završe
                int[][] buffer = new int[canvasWidth][canvasHeight];

                //za progress bar
                AtomicInteger progress = new AtomicInteger(0);

                //Upali 4 threada i daj im zadatke
                ExecutorService executor = Executors.newFixedThreadPool(4);

                //onda jos saljemo progress svakom threadu i one ga updateaju
                executor.submit(new JuliaSetCalculator(canvas, 1, buffer, real, imaginary, maxIter, juliaSetParameters, progress));
                executor.submit(new JuliaSetCalculator(canvas, 2, buffer, real, imaginary, maxIter, juliaSetParameters, progress));
                executor.submit(new JuliaSetCalculator(canvas, 3, buffer, real, imaginary, maxIter, juliaSetParameters, progress));
                executor.submit(new JuliaSetCalculator(canvas, 4, buffer, real, imaginary, maxIter, juliaSetParameters, progress));

                //kill the executor
                executor.shutdown();

                while(!executor.isTerminated()) {
                    if(executor.awaitTermination(50, TimeUnit.MILLISECONDS))
                        break;

                    updateProgress(progress.get(), totalPixels);
                }

                updateProgress(totalPixels, totalPixels);
                long endTime = System.currentTimeMillis();
                setVrijeme(startTime, endTime);

                setBuffer(buffer);

                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                draw(buffer);
            }

            @Override
            protected void failed() {
                super.failed();
                progressBar.progressProperty().unbind();
                System.out.println("Thread je failao");
            }

            @Override
            protected void cancelled() {
                super.cancelled();
                progressBar.progressProperty().unbind();
                System.out.println("Thread je otkazan");
            }
        };

        progressBar.progressProperty().unbind();
        progressBar.setProgress(0);
        progressBar.progressProperty().bind(calculateTask.progressProperty());

        new Thread(calculateTask).start();
    }
    
    public long getRenderTime(){
        return vrijeme;
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

    private void draw(int buffer[][]) {

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
                    pixwrite.setColor(x, y, Color.hsb(360 * (makeColor((double) iteracija, maxIter,colorValue)), 1.0, 1.0));
            }
        }
    }

    private void setVrijeme(long start, long end) {
        this.vrijeme = start-end;
    }

    private void setBuffer(int[][] buffer) {
        this.buffer = buffer;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    private double makeColor(double iteracija,int maxIter,double colorValue){
        double newColor;
        if((iteracija/maxIter)+colorValue>1){
            newColor=(iteracija/maxIter)+colorValue-1;
            return newColor;
        }
        else{
            newColor =(iteracija/maxIter)+colorValue;
            return newColor;
        }
    }
}
