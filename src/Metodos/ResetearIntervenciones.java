package Metodos;

import java.sql.*;
import java.util.Scanner;

public class ResetearIntervenciones {

    public static void resetearIntervenciones(){
        String query = "UPDATE alumnos SET intervenciones = 0";
        try {
            System.out.println("Escriba la ruta de su base de datos:");
            Scanner export1 = new Scanner(System.in);
            String rutaDatabase = export1.nextLine();
            Connection connection = DriverManager.getConnection(rutaDatabase);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
    }
}
