package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrearDatabase {
    public static void CrearDatabase() {
        System.out.println("Esta seguro que desea crear una Base de Datos ?" +
                " \nSi es asi escriba 'si', por el contrario si no desea crear ninguna Base de Datos escriba 'cancelar'");

        Scanner scanner = new Scanner(System.in);
        String scannertexto = scanner.nextLine();


        if (scannertexto.equalsIgnoreCase("si")) {
            System.out.println("Escriba la ruta de su Base de Datos");
            Scanner crearDB = new Scanner(System.in);
            String rutaDatabase = crearDB.nextLine();
            //Para introducir la ruta de la base de datos sería de este estilo: jdbc:mariadb://localhost:3306/

            System.out.println("Escriba el nombre ");
            Scanner nombreDatabase = new Scanner(System.in);
            String nombreBase = scanner.nextLine();
            //A la hora

            try (Connection conn = DriverManager.getConnection(rutaDatabase);
                 Statement stmt = conn.createStatement()) {
                String sql = "CREATE OR REPLACE DATABASE " + nombreBase;
                stmt.executeUpdate(sql);
                System.out.println("Base de datos creada con exito!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (scannertexto.equalsIgnoreCase("cancelar")) {
            System.out.println("No se ha creado ninguna base de datos");
        }
    }
}