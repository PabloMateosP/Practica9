package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrearTablaDatabase {
    public static void CrearTablaDatabase() {

        System.out.println("Esta seguro que desea crear una nueva tabla en la Base de Datos ?" +
                " \nSi es asi escriba si, por el contrario si no desea crear ninguna tabla " +
                "\na la Base de Datos escriba cancelar");
        Scanner scanner = new Scanner(System.in);
        String scannertexto = scanner.nextLine();


        if (scannertexto.equalsIgnoreCase("si")) {
            Scanner crearTabla = new Scanner(System.in);
            System.out.println("Escriba la ruta de su Base de Datos");
            String rutaDatabase = crearTabla.nextLine();
            System.out.println("Escriba el nombre de la tabla");
            Scanner nombreTabla = new Scanner(System.in);
            String nombreTABLA = scanner.nextLine();


            try (Connection conn = DriverManager.getConnection(rutaDatabase);
                 Statement stmt = conn.createStatement()) {
                String sql = "CREATE TABLE " + nombreTABLA + " (nombre VARCHAR(40), intervenciones INT, " +
                        "fecha_intervencion TIMESTAMP)";
                //TODO: SI EN EL FUTURO NOS QUEDARA TIEMPO METER LOS PARÁMETROS POR SCANNER

                stmt.executeUpdate(sql);
                System.out.println("Tabla creada con exito!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (scannertexto.equalsIgnoreCase("cancelar")) {
            System.out.println("La base de datos no ha sido modificada");
        }
    }
}

