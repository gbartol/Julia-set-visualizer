package com.pmf.juliasetvisualizer.calculators;

import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import com.pmf.juliasetvisualizer.ui.JuliaSetCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuliaSetCalculatorTest {
    @Test
    void checkPath() {
        System.out.println("Current Library Path: " + System.getProperty("java.library.path"));
    }
    @Test
    void brojStoURealJeMinusJedanTockaDva(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        JuliaSetParameters juliaSetParameters=new JuliaSetParameters(0,0,1,0,0,100);

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter,juliaSetParameters);

        double testRješenje=-1.2;
        assertEquals(testRješenje,novicalculator.mapToReal(100));

    }
    @Test
    void brojPetstoURealJeDva(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        JuliaSetParameters juliaSetParameters=new JuliaSetParameters(0,0,1,0,0,100);

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter,juliaSetParameters);

        assertEquals(2,novicalculator.mapToReal(500));
    }
    @Test
    void brojNulaURealJeMinusDva(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        JuliaSetParameters juliaSetParameters=new JuliaSetParameters(0,0,1,0,0,100);

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter,juliaSetParameters);

        assertEquals(-2,novicalculator.mapToReal(0));
    }

    @Test
    void brojStoUImagJeMinusJedanTockaDva(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        JuliaSetParameters juliaSetParameters=new JuliaSetParameters(0,0,1,0,0,100);

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter,juliaSetParameters);

        double testRješenje=-1.2;
        assertEquals(testRješenje,novicalculator.mapToImaginary(100));

    }
    @Test
    void brojPetstoUImagJeDva(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        JuliaSetParameters juliaSetParameters=new JuliaSetParameters(0,0,1,0,0,100);

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter,juliaSetParameters);

        assertEquals(2,novicalculator.mapToImaginary(500));
    }
    @Test
    void brojNulaUImagJeMinusDva(){
        JuliaSetCanvas canvas=new JuliaSetCanvas();
        int quadrant=1;
        int [][] buffer= new int[500][500];
        double cReal=0.5;
        double cImaginary=0.5;
        int maxIter=100;
        JuliaSetParameters juliaSetParameters=new JuliaSetParameters(0,0,1,0,0,100);

        JuliaSetCalculator novicalculator=new JuliaSetCalculator(canvas,quadrant,buffer, cReal,cImaginary,maxIter,juliaSetParameters);

        assertEquals(-2,novicalculator.mapToImaginary(0));
    }

}