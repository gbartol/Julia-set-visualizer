package com.pmf.juliasetvisualizer.calculators;

import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuliaSetCalculatorTest {

    @Test
    void stoUDouble(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        GraphicsContext gc;
        PixelWriter pw;

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter);

        double testRješenje=100;
        assertEquals(testRješenje,novicalculator.mapToReal(100));

    }
    @Test
    void stoNijeNull(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        GraphicsContext gc;
        PixelWriter pw;

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter);

        assertTrue(novicalculator.mapToReal(100)==100);
    }

}