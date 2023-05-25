package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BorrarDatabase {
    public static void BorrarDatabase() {

        System.out.println("Esta seguro que desea borrar la Base de Datos ?" +
                " \nSi es asi escriba si, por el contrario si no desea borrar ninguna Base de Datos escriba cancelar");
        Scanner scanner = new Scanner(System.in);
        String scannertexto = scanner.nextLine();


        if (scannertexto.equalsIgnoreCase("si")) {
            Scanner borrarDB = new Scanner(System.in);
            System.out.println("Escriba la ruta de su Base de Datos");
            String rutaDatabase = borrarDB.nextLine();
            System.out.println("Escriba el nombre ");
            Scanner nombreDatabase = new Scanner(System.in);
            String nombreBase = scanner.nextLine();


            try (Connection conn = DriverManager.getConnection(rutaDatabase);
                 Statement stmt = conn.createStatement()) {
                String sql = "DROP DATABASE " + nombreBase;
                stmt.executeUpdate(sql);
                System.out.println("Base de datos borrada con exito!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (scannertexto.equalsIgnoreCase("cancelar")) {
            System.out.println("La base de datos no ha sido modificada");
        }

    }

}
