package Pruebas;

import Metodos.BorrarDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrarDatabaseINTERFAZ {
    public static void BorrarDatabase (String rutaDatabase, String nombreBase){
        try (Connection conn = DriverManager.getConnection(rutaDatabase);
             Statement stmt = conn.createStatement()) {
            String sql = "DROP DATABASE " + nombreBase;
            stmt.executeUpdate(sql);
            System.out.println("Base de datos borrada con exito!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
