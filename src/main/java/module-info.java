module com.pmf.juliasetvisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.swing;  // Omogućuje pristup SwingFXUtils
    requires java.desktop;


    exports com.pmf.juliasetvisualizer;
    exports com.pmf.juliasetvisualizer.ui;
    exports com.pmf.juliasetvisualizer.calculators;
    exports com.pmf.juliasetvisualizer.controllers;
    exports com.pmf.juliasetvisualizer.models;
    exports com.pmf.juliasetvisualizer.db;

    opens com.pmf.juliasetvisualizer to javafx.graphics, javafx.fxml;
    opens com.pmf.juliasetvisualizer.ui to javafx.graphics, javafx.fxml;
}