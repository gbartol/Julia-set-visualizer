package com.pmf.juliasetvisualizer.calculators;

import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaSetCalculator implements Runnable {

    private int quadrant;
    private int[][] buffer;
    private int maxIter;
    private double cReal;
    private double cImaginary;
    public static JuliaSetCanvas canvas;
    private JuliaSetParameters juliaSetParameters;
    private int canvasWidth;
    private int canvasHeight;

    static {
        System.loadLibrary("calculator");
    }

    public JuliaSetCalculator(JuliaSetCanvas canvas,int quadrant, int[][] buffer, double cReal, double cImaginary, int maxIter, JuliaSetParameters juliaSetParameters) {
        this.juliaSetParameters = juliaSetParameters;

        this.quadrant = quadrant;
        this.buffer = buffer;
        this.cReal = cReal;
        this.cImaginary = cImaginary;
        this.maxIter = maxIter;
        JuliaSetCalculator.canvas =canvas;
        canvasWidth = (int) canvas.getWidth();
        canvasHeight = (int) canvas.getHeight();
        if(JuliaSetCalculator.canvas ==null){
            System.out.println("canvas je null u Calculatoru");
        }
        System.out.println("Pokrenuo novi JuliaSetCalculator");
        JuliaSetCalculator.canvas.kontrolniint=5;
        //run(); //Tu ne pozivas funkciju run nego se to radi u controlleru sa imeThreada.start()
    }

    @Override
    public void run() {
        System.out.println("Idem računat za "+quadrant+". kvadrant");
        //KAD IMPLEMENTIRAMO SCALE OPCIJU /10 ZAMJENITI S TIM
        // I KAD DODAMO OPCIJU ZA MICANJE U CALCULATE MORA IC i/scale (ili inverz scalea???) + x-offset
        //ISTO ZA j/scale +y-offset

        int iteracije;
        /*GraphicsContext gc = canvas.getGraphicsContext2D();
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
                double z0Real = juliaSetParameters.mapPixelToReal(i, (int) canvas.getWidth());
                double z0Imaginary = juliaSetParameters.mapPixelToImaginary(j, 500);
                //double z0Real = juliaSetParameters.mapToReal(i);
                //double z0Imaginary = juliaSetParameters.mapToImaginary(j);
                
                int iteracija = calculate(maxIter, cReal, cImaginary, z0Real, z0Imaginary);
                buffer[i][j]  =iteracija;
            }
        }

    }

    
    protected double mapToReal(int i){
        // skaliraj na [0, 4] pa -2 shift u [-2, 2]
        return 4.0 * ((double) i /500) -2.0;
    }
    
    protected double mapToImaginary(int j){
        // skaliraj na [0, 4] pa -2 shift u [-2, 2]
        return 4.0 * ((double) j /500) -2.0;
    }
    
    private native int calculate(int maxIteracije, double cReal, double cImaginary, double z0Real, double z0Imaginary);
}
