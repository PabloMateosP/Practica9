package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrearDatabaseINTERFAZ {
    public static void CrearDatabase(String rutaDatabase, String nombreBase) {



        try (Connection conn = DriverManager.getConnection(rutaDatabase);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE OR REPLACE DATABASE " + nombreBase;
            stmt.executeUpdate(sql);
            System.out.println("Base de datos creada con exito!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}