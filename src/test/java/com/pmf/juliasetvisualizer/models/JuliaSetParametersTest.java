package com.pmf.juliasetvisualizer.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuliaSetParametersTest {

    @Test
    void mapBrojStoToRealToPetSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-1.2,noviparametri.mapPixelToReal(100,500));
    }
    @Test
    void mapBrojStoToRealToTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-1.6,noviparametri.mapPixelToReal(100,1000));
    }
    @Test
    void mapBrojStoToImagToPetSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-1.2,noviparametri.mapPixelToImaginary(100,500));
    }
    @Test
    void mapBrojStoToImagToTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-1.6,noviparametri.mapPixelToImaginary(100,1000));
    }
    @Test
    void mapBrojPetStoToRealToPetSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(2,noviparametri.mapPixelToReal(500,500));
    }
    @Test
    void mapBrojPetStoToRealToTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(0,noviparametri.mapPixelToReal(500,1000));
    }
    @Test
    void mapBrojPetStoToImagToPetSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(2,noviparametri.mapPixelToImaginary(500,500));
    }
    @Test
    void mapBrojPetStoToImagToTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(0,noviparametri.mapPixelToImaginary(500,1000));
    }
    @Test
    void mapBrojNulaToRealToPetSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-2,noviparametri.mapPixelToReal(0,500));
    }
    @Test
    void mapBrojNulaToRealToTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-2,noviparametri.mapPixelToReal(0,1000));
    }
    @Test
    void mapBrojNulaToImagToPetSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-2,noviparametri.mapPixelToImaginary(0,500));
    }
    @Test
    void mapBrojNulaToImagToTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(-2,noviparametri.mapPixelToImaginary(0,1000));
    }
    @Test
    void getCentarXNula() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(0,noviparametri.getCenterX());
    }
    @Test
    void getCentarXSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(100, 0, 1,0, 0, 100);
        assertEquals(100,noviparametri.getCenterX());
    }
    @Test
    void getCentarYNula() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(0,noviparametri.getCenterY());
    }
    @Test
    void getCentarYSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 100, 1,0, 0, 100);
        assertEquals(100,noviparametri.getCenterY());
    }
    @Test
    void getZoomJedan() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(1,noviparametri.getZoom());
    }
    @Test
    void getZoomDeset() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 10,0, 0, 100);
        assertEquals(10,noviparametri.getZoom());
    }
    @Test
    void getZoomSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 100,0, 0, 100);
        assertEquals(100,noviparametri.getZoom());
    }
    @Test
    void getCRealNula() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(0,noviparametri.getcReal());
    }
    @Test
    void getCRealDeset() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,10, 0, 100);
        assertEquals(10,noviparametri.getcReal());
    }
    @Test
    void getCRealSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,100, 0, 100);
        assertEquals(100,noviparametri.getcReal());
    }
    @Test
    void getCImagNula() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 100);
        assertEquals(0,noviparametri.getcImaginary());
    }
    @Test
    void getCImagDeset() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 10, 100);
        assertEquals(10,noviparametri.getcImaginary());
    }
    @Test
    void getCImagSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 100, 100);
        assertEquals(100,noviparametri.getcImaginary());
    }
    @Test
    void getMaxIterJedan() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 0, 1);
        assertEquals(1,noviparametri.getMaxIterations());
    }
    @Test
    void getMacIterDeset() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 10, 10);
        assertEquals(10,noviparametri.getMaxIterations());
    }
    @Test
    void getMacIterSto() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 100, 100);
        assertEquals(100,noviparametri.getMaxIterations());
    }
    @Test
    void getMacIterTisucu() {
        JuliaSetParameters noviparametri=new JuliaSetParameters(0, 0, 1,0, 100, 1000);
        assertEquals(1000,noviparametri.getMaxIterations());
    }


    @Test
    void mapPixelToImaginary() {
    }

    @Test
    void getCenterX() {
    }

    @Test
    void getCenterY() {
    }

    @Test
    void getZoom() {
    }

    @Test
    void getcReal() {
    }

    @Test
    void getcImaginary() {
    }

    @Test
    void getMaxIterations() {
    }
}