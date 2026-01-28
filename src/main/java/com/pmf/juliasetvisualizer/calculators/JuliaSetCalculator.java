package com.pmf.juliasetvisualizer.calculators;

public class JuliaSetCalculator implements Runnable {
    private int quadrant;
    private double real;
    private double imaginary;

    public JuliaSetCalculator(int quadrant, double real, double imaginary) {
        this.quadrant = quadrant;
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public void run() {
        calculate(quadrant, real, imaginary);
    }

    private native int calculate(int quadrant, double real, double imaginary);
}
