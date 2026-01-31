package com.pmf.juliasetvisualizer.models;

// Klasa za parametre skupa
// Biti će korisno za bazu podataka kasnije
public class JuliaSetParameters {

    private double centerX;
    private double centerY;
    private double zoom;
    private double cReal;
    private double cImaginary;
    private int maxIterations;

    public JuliaSetParameters(double centerX, double centerY, double zoom, double cReal, double cImaginary, int maxIterations) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.zoom = zoom;
        this.cReal = cReal;
        this.cImaginary = cImaginary;
        this.maxIterations = maxIterations;
    }
}
