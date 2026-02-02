package com.pmf.juliasetvisualizer.models;

// Klasa za parametre skupa
// Biti će korisno za bazu podataka kasnije
public class JuliaSetParameters {

    private int id;
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
    
    public JuliaSetParameters(int id, double centerX, double centerY, double zoom, double cReal, double cImaginary, int maxIterations) {
        this.id = id;
        this.centerX = centerX;
        this.centerY = centerY;
        this.zoom = zoom;
        this.cReal = cReal;
        this.cImaginary = cImaginary;
        this.maxIterations = maxIterations;
    }

    public double mapPixelToReal(int px, int canvasWidth) {
        double range = 4.0 / zoom;
        double minReal = centerX - range / 2;
        return minReal + (px * range / canvasWidth);
    }
    public double mapPixelToImaginary(int py, int canvasHeight) {
        double range = 4.0 / zoom;
        double minImag = centerY - range / 2;
        return minImag + (py * range / canvasHeight);
    }
    
    public int getId(){
        return id;
    }
    
    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getZoom() {
        return zoom;
    }

    public double getcReal() {
        return cReal;
    }

    public double getcImaginary() {
        return cImaginary;
    }

    public int getMaxIterations() {
        return maxIterations;
    }
    
    @Override
    public String toString(){
        return String.format("c=%.3f+%.3fi, center=(%.3f,%.3f), zoom=%.2f, N=%d", cReal, cImaginary, centerX, centerY, zoom, maxIterations);
    }
}
