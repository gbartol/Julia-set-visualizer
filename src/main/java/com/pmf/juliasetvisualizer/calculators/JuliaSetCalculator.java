package com.pmf.juliasetvisualizer.calculators;

import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaSetCalculator implements Runnable {

    private int quadrant;
    private double real;
    private double imaginary;
    public static JuliaSetCanvas Canvas;

    public JuliaSetCalculator(JuliaSetCanvas canvas,int quadrant, double real, double imaginary) {

        this.quadrant = quadrant;
        this.real = real;
        this.imaginary = imaginary;
        Canvas=canvas;
        if(Canvas==null){
            System.out.println("Canvas je null u Calculatoru");
        }
        System.out.println("Pokrenuo novi JuliaSetCalculator");
        Canvas.kontrolniint=5;
        System.out.println("kontrolniint je "+Canvas.kontrolniint);
        run(); //Tu ne pozivas funkciju run nego se to radi u controlleru sa imeThreada.start()
    }

    @Override
    public void run() {
        System.out.println("Idem računat za "+quadrant+". kvadrant");
        //KAD IMPLEMENTIRAMO SCALE OPCIJU /10 ZAMJENITI S TIM
        // I KAD DODAMO OPCIJU ZA MICANJE U CALCULATE MORA IC i/scale (ili inverz scalea???) + x-offset
        //ISTO ZA j/scale +y-offset

        int iteracije;
        GraphicsContext gc = Canvas.getGraphicsContext2D();
        PixelWriter pw = gc.getPixelWriter();
        double k;
        double l;
        if(quadrant==1){
            for(int i=250;i<500;i++){
                for(int j=0;j<250;j++){
                    k=i;
                    l=j;
                    iteracije=calculate(quadrant,k ,l );
                    getColor(pw,iteracije,i,j);
                }
            }
        } else if (quadrant==2) {
            for(int i=0;i<250;i++){
                for(int j=0;j<250;j++){
                    k=i;
                    l=j;
                    iteracije=calculate(quadrant,k ,l );
                    getColor(pw,iteracije,i,j);
                }
            }
        } else if (quadrant==3) {
            for(int i=0;i<250;i++){
                for(int j=250;j<500;j++){
                    k=i;
                    l=j;
                    iteracije=calculate(quadrant,k ,l );
                    getColor(pw,iteracije,i,j);
                }
            }
        } else if (quadrant==4) {
            for(int i=250;i<500;i++){
                for(int j=250;j<500;j++){
                    k=i;
                    l=j;
                    iteracije=calculate(quadrant,k ,l );
                    getColor(pw,iteracije,i,j);
                }
            }

        }

    }
    public void getColor(PixelWriter pw,int iteracije,int i,int j){
        if(iteracije==100){
            pw.setColor(i,j, Color.BLACK);
        } else if (iteracije<100 && iteracije>50) {
            pw.setColor(i,j, Color.GREEN);
        } else if (iteracije<50) {
            pw.setColor(i,j, Color.BLUE);
        }
    }

    private native int calculate(int quadrant, double real, double imaginary);
}
