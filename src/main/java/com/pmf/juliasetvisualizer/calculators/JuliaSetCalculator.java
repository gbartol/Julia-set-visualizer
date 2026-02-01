package com.pmf.juliasetvisualizer.calculators;

import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaSetCalculator implements Runnable {

    private int quadrant;
    private int[][] buffer;
    private int maxIter;
    private double cReal;
    private double cImaginary;
    public static JuliaSetCanvas Canvas;

    public JuliaSetCalculator(JuliaSetCanvas canvas,int quadrant, int[][] buffer, double cReal, double cImaginary, int maxIter) {

        this.quadrant = quadrant;
        this.buffer = buffer;
        this.cReal = cReal;
        this.cImaginary = cImaginary;
        this.maxIter = maxIter;
        Canvas=canvas;
        if(Canvas==null){
            System.out.println("Canvas je null u Calculatoru");
        }
        System.out.println("Pokrenuo novi JuliaSetCalculator");
        Canvas.kontrolniint=5;
        System.out.println("kontrolniint je "+Canvas.kontrolniint);
        //run(); //Tu ne pozivas funkciju run nego se to radi u controlleru sa imeThreada.start()
    }

    @Override
    public void run() {
        System.out.println("Idem računat za "+quadrant+". kvadrant");
        //KAD IMPLEMENTIRAMO SCALE OPCIJU /10 ZAMJENITI S TIM
        // I KAD DODAMO OPCIJU ZA MICANJE U CALCULATE MORA IC i/scale (ili inverz scalea???) + x-offset
        //ISTO ZA j/scale +y-offset

        int iteracije;
        /*GraphicsContext gc = Canvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        
        Dretve ne crtaju po canvasu, vec pune buffer
        Onda controller preko buffera crta canvas.
        */
        int pocetakX, pocetakY, krajX, krajY;
        
        switch (quadrant) {
            case 1 -> {
                pocetakX = 250;
                krajX = 500;
                pocetakY = 0;
                krajY = 250;
            }
            case 2 -> {
                pocetakX = 0;
                krajX = 250;
                pocetakY = 0;
                krajY = 250;
            }
            case 3 -> {
                pocetakX = 0;
                krajX = 250;
                pocetakY = 250;
                krajY = 500;
            }
            case 4 -> {
                pocetakX = 250;
                krajX = 500;
                pocetakY = 250;
                krajY = 500;
            }
            default -> throw new IllegalArgumentException("Kvadrant mora biti 1-4");
        }
        
        for( int i = pocetakX; i<krajX; i++)
        {
            for(int j = pocetakY; j<krajY; j++)
            {
                double z0Real = mapToReal(i);
                double z0Imaginary = mapToImaginary(j);
                
                int iteracija = calculate(maxIter, cReal, cImaginary, z0Real, z0Imaginary);
                buffer[i][j]  =iteracija;
            }
        }

    }
    
    //Na kraju ce ovo biti useless LOL
    public void getColor(PixelWriter pw,int iteracije,int i,int j){
        if(iteracije==100){
            pw.setColor(i,j, Color.BLACK);
        } else if (iteracije<100 && iteracije>50) {
            pw.setColor(i,j, Color.GREEN);
        } else if (iteracije<50) {
            pw.setColor(i,j, Color.BLUE);
        }
    }
    
    private double mapToReal(int i){
        // skaliraj na [0, 4] pa -2 shift u [-2, 2]
        return 4.0 * (i/500) -2.0;
    }
    
    private double mapToImaginary(int j){
        // skaliraj na [0, 4] pa -2 shift u [-2, 2]
        return 4.0 * (j/500) -2.0;
    }
    
    private native int calculate(int maxIteracije, double cReal, double cImaginary, double z0Real, double z0Imaginary);
}
