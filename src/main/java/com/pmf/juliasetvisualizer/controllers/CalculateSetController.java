package com.pmf.juliasetvisualizer.controllers;

import com.pmf.juliasetvisualizer.calculators.JuliaSetCalculator;
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
    public static JuliaSetCanvas Canvas;

    public CalculateSetController(JuliaSetCanvas canvas) {
        this.real = Double.parseDouble(ControlPanel.realTextField.getText());
        this.imaginary = Double.parseDouble(imaginaryTextField.getText());
        System.out.println(this.real);
        System.out.println(this.imaginary);
        Canvas=canvas;
        if(Canvas==null){
            System.out.println("Canvas je null u Controlleru");
        }
        Canvas.kontrolniint=4;
        System.out.println("kontrolniint je "+Canvas.kontrolniint);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if(!isValidInput()) return;
        
        double real = Double.parseDouble(ControlPanel.realTextField.getText());
        double imaginary = Double.parseDouble(imaginaryTextField.getText());
        int maxIter = (int) ControlPanel.getMaxIterationsSlider().getValue();
        long startTime=System.currentTimeMillis();
       /* 
        Thread thread1 = new Thread(new JuliaSetCalculator(Canvas,1, real, imaginary));
        Thread thread2 = new Thread(new JuliaSetCalculator(Canvas,2, real, imaginary));
        Thread thread3 = new Thread(new JuliaSetCalculator(Canvas,3, real, imaginary));
        Thread thread4 = new Thread(new JuliaSetCalculator(Canvas,4, real, imaginary));
        Ovako bi bilo problematično s koordinacijom. Lakše s Executorom
        */
       
       // U buffer će svaka dretva spremati rezultat. on će se ispisati tek nakon što sve dretve završe
       int[][] buffer = new int[500][500];
       
       //Upali 4 threada i daj im zadatke
       ExecutorService executor = Executors.newFixedThreadPool(4);
       
       executor.submit(new JuliaSetCalculator(ControlPanel.Canvas, 1, buffer, real, imaginary, maxIter));
       executor.submit(new JuliaSetCalculator(ControlPanel.Canvas, 2, buffer, real, imaginary, maxIter));
       executor.submit(new JuliaSetCalculator(ControlPanel.Canvas, 3, buffer, real, imaginary, maxIter));
       executor.submit(new JuliaSetCalculator(ControlPanel.Canvas, 4, buffer, real, imaginary, maxIter));
       
       //kill the executor
       executor.shutdown();
       try{
           executor.awaitTermination(60, TimeUnit.SECONDS);
       }catch(InterruptedException e){
           e.printStackTrace();
       }
        //System.out.println(buffer);
       
       Platform.runLater(()-> {
           GraphicsContext graphCont = Canvas.getGraphicsContext2D();
           PixelWriter pixwrite = graphCont.getPixelWriter();
           //Time for crtanje
           for(int x = 0; x < 500; x++)
           {
               for(int y = 0; y<500; y++)
               {
                   int iteracija = buffer[x][y];
                   if(iteracija == maxIter) 
                       pixwrite.setColor(x, y, Color.BLACK);
                   else
                       //hsb = hue, saturation, brightness. hue je 360*t za 0<=t<=1
                       pixwrite.setColor(x, y, Color.hsb(360* (iteracija/maxIter), 1.0, 1.0));
               }
           }
           
           
       });
       
        long endTime=System.currentTimeMillis();
        long vrijeme=endTime-startTime;
        System.out.println("Vrijeme je "+vrijeme);
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
