package com.pmf.juliasetvisualizer.db;

import static com.pmf.juliasetvisualizer.db.Database.getConnection;
import com.pmf.juliasetvisualizer.models.JuliaSetParameters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JuliaSetDAO {

    private static final String URL = "jdbc:sqlite:juliasets.db";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS julia_set (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "c_real REAL NOT NULL," +
            "c_imaginary REAL NOT NULL," +
            "center_x REAL NOT NULL," +
            "center_y REAL NOT NULL," +
            "zoom REAL NOT NULL," +
            "max_iterations INTEGER NOT NULL," +
            "render_time_ms INTEGER," +
            "color_value REAL NOT NULL," +
            "created_at TEXT DEFAULT CURRENT_TIMESTAMP" +

            ");";

    // init
    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            if (conn != null) {
                stmt.execute(SQL_CREATE_TABLE);
            }

        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
    
    
    //Create / insert
    public static void save(JuliaSetParameters p, long renderTime){
        String sql = "INSERT INTO julia_set " +
                "(c_real, c_imaginary, center_x, center_y, zoom, max_iterations, render_time_ms , color_value) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        
         try (Connection conn = DriverManager.getConnection(URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setDouble(1, p.getcReal());
                pstmt.setDouble(2, p.getcImaginary());
                pstmt.setDouble(3, p.getCenterX());
                pstmt.setDouble(4, p.getCenterY());
                pstmt.setDouble(5, p.getZoom());
                pstmt.setInt(6, p.getMaxIterations());
                pstmt.setLong(7, renderTime);
                pstmt.setDouble(8, p.getColorValue());

                pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving Julia set: " + e.getMessage());
        }
    
    }
    
    
     //select 
     public static List<JuliaSetParameters> selectAll() {
        List<JuliaSetParameters> list = new ArrayList<>();
        String sql = "SELECT * FROM julia_set";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new JuliaSetParameters(
                        rs.getInt("id"),
                        rs.getDouble("center_x"),
                        rs.getDouble("center_y"),
                        rs.getDouble("zoom"),
                        rs.getDouble("c_real"),
                        rs.getDouble("c_imaginary"),
                        rs.getInt("max_iterations"),
                        rs.getDouble("color_value")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error reading Julia sets: " + e.getMessage());
        }

        return list;
    }
     
     public static void delete(int id) {
        String sql = "DELETE FROM julia_set WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error deleting Julia set: " + e.getMessage());
        }
    }
     
     //Update
     
    public static void update(int id, JuliaSetParameters p, long renderTime) {
        String sql = "UPDATE julia_set SET " +
                "c_real = ?, " +
                "c_imaginary = ?, " +
                "center_x = ?, " +
                "center_y = ?, " +
                "zoom = ?, " +
                "max_iterations = ?, " +
                "render_time_ms = ? " +
                "color_value = ? " +
                "WHERE id = ?";

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, p.getcReal());
            pstmt.setDouble(2, p.getcImaginary());
            pstmt.setDouble(3, p.getCenterX());
            pstmt.setDouble(4, p.getCenterY());
            pstmt.setDouble(5, p.getZoom());
            pstmt.setInt(6, p.getMaxIterations());
            pstmt.setLong(7, renderTime);
            pstmt.setDouble(8, p.getColorValue());
            pstmt.setInt(9, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating Julia set: " + e.getMessage());
        }
    } 
}

