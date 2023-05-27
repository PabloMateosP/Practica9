package Metodos;

import java.sql.*;
import java.util.Scanner;

public class ResetearIntervenciones {

    public static void resetearIntervenciones(){
        System.out.println("Esta seguro de que desea poner todas las intervenciones a cero?" +
                " \nSi es asi escriba si, por el contrario si no desea realizar esta accion escriba cancelar");
        Scanner export0 = new Scanner(System.in);
        String rutaEntrar = export0.nextLine();

        if (rutaEntrar.equalsIgnoreCase("si")){
            String query = "UPDATE alumnos SET intervenciones = 0";
            try {
                System.out.println("Escriba la ruta de su base de datos:");
                Scanner export1 = new Scanner(System.in);
                String rutaDatabase = export1.nextLine();
                Connection connection = DriverManager.getConnection(rutaDatabase);
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);

                System.out.println("Intervenciones reseteadas correctamente");
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        } else if (rutaEntrar.equalsIgnoreCase("cancelar")) {
            System.out.println("La base de datos no ha sido modificada.");
        }



    }
}
