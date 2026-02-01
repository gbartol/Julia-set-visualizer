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

    public double mapToReal(int i){
        // skaliraj na [0, 4] pa -2 shift u [-2, 2]
        return 4.0 * ((double) i /500) -2.0;
    }

    public double mapToImaginary(int j){
        // skaliraj na [0, 4] pa -2 shift u [-2, 2]
        return 4.0 * ((double) j /500) -2.0;
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
}
