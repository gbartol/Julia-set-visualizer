module com.pmf.juliasetvisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    exports com.pmf.juliasetvisualizer;
    exports com.pmf.juliasetvisualizer.ui;
    exports com.pmf.juliasetvisualizer.calculators;
    exports com.pmf.juliasetvisualizer.controllers;

    opens com.pmf.juliasetvisualizer to javafx.graphics, javafx.fxml;
    opens com.pmf.juliasetvisualizer.ui to javafx.graphics, javafx.fxml;
}