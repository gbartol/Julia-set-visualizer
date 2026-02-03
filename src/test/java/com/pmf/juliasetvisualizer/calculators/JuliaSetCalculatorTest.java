package com.pmf.juliasetvisualizer.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuliaSetCalculatorTest {
    static {
        System.loadLibrary("calculator");
    }
    @Test
    void calculateNulaNulaNulaNula(){
        int iteracije=JuliaSetCalculator.calculate(100,0,0,0,0);
        assertEquals(100,iteracije);
    }
    @Test
    void calculateNulaNulaNulaDva(){
        int iteracije=JuliaSetCalculator.calculate(100,0,0,0,2);
        assertEquals(0,iteracije);
    }
    @Test
    void calculateNulaNulaNulaJedan(){
        int iteracije=JuliaSetCalculator.calculate(100,0,0,0,1);
        assertEquals(100,iteracije);
    }
    @Test
    void calculateNulaJedanNulaJedan(){
        int iteracije=JuliaSetCalculator.calculate(100,0,1,0,1);
        assertEquals(100,iteracije);
    }
    @Test
    void calculateJedanJedanNulaNula(){
        int iteracije=JuliaSetCalculator.calculate(100,1,1,0,0);
        assertEquals(2,iteracije);
    }
    @Test
    void calculateNulaTockaPetNulaTockaPetNulaNula(){
        int iteracije=JuliaSetCalculator.calculate(100,0.5,0.5,0,0);
        assertEquals(5,iteracije);
    }
    @Test
    void calculateNulaTockaČetriNulaTockaČetriNulaNula(){
        int iteracije=JuliaSetCalculator.calculate(100,0.4,0.4,0,0);
        assertEquals(9,iteracije);
    }
    @Test
    void calculateNulaTockaTrišestšestNulaTockaTrišestšestNulaNula(){
        int iteracije=JuliaSetCalculator.calculate(100,0.366,0.366,0,0);
        assertEquals(22,iteracije);
    }

    @Test
    void calculateNulaTockaDvadesetpetNulaTockaDvadesetpetNulaNula(){
        int iteracije=JuliaSetCalculator.calculate(100,0.25,0.25,0,0);
        assertEquals(100,iteracije);
    }
    @Test
    void calculateNulaTockaDvadesetpetNulaTockaDvadesetpetNulaTockaŠestšestNulaTockaŠestšest(){
        int iteracije=JuliaSetCalculator.calculate(100,0.25,0.25,0.66,0.66);
        assertEquals(5,iteracije);
    }
    @Test
    void calculateTisucuNulaTockaDvadesetpetNulaTockaDvadesetpetNulaTockaDvadesetpetNulaTockaDvadesetpet(){
        int iteracije=JuliaSetCalculator.calculate(1000,0.25,0.25,0.25,0.25);
        assertEquals(1000,iteracije);
    }
    @Test
    void calculateTisucuNulaTockapetNulaTockapetNulaTockapetNulaTockapet(){
        int iteracije=JuliaSetCalculator.calculate(1000,0.5,0.5,0.5,0.5);
        assertEquals(4,iteracije);
    }

}