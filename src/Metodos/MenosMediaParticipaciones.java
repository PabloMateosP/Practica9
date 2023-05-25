package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MenosMediaParticipaciones {

    public static void seleccionarAlumnosConMenosDeMedia() {
        try {


            Scanner crearTabla = new Scanner(System.in);
            System.out.println("Escriba la ruta de su Base de Datos");
            String rutaDatabase = crearTabla.nextLine();
            Connection connection= DriverManager.getConnection(rutaDatabase);


            Statement avgStatement = connection.createStatement();
            String avgQuery = "SELECT AVG(intervenciones) AS media_intervenciones FROM alumnos";
            ResultSet avgResultSet = avgStatement.executeQuery(avgQuery);
            avgResultSet.next();
            double media = avgResultSet.getDouble("media_intervenciones");
            avgResultSet.close();
            avgStatement.close();


            Statement statement = connection.createStatement();
            String query = "SELECT nombre, intervenciones FROM alumnos WHERE intervenciones < " + media;
            ResultSet resultSet = statement.executeQuery(query);


            if (!resultSet.isBeforeFirst()) {
                System.out.println("No hay alumnos con menos intervenciones que la media.");
            } else {
                System.out.println("Alumnos con menos de la media de participaciones (" + media + "):");
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    int intervenciones = resultSet.getInt("intervenciones");
                    System.out.println("Nombre: " + nombre + "\nIntervenciones: " + intervenciones);
                    System.out.println("---------------------");
                }
            }


            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



