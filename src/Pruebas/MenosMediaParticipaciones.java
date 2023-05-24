package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MenosMediaParticipaciones {
    public static void main(String[] args) {
        // Establecer la conexión a la base de datos
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/intervenciones");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Seleccionar alumnos con menos de la media de participaciones
        seleccionarAlumnosConMenosDeMedia(connection);


        // Cerrar la conexión a la base de datos
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void seleccionarAlumnosConMenosDeMedia(Connection connection) {
        try {
            // Calcular la media de participaciones
            Statement avgStatement = connection.createStatement();
            String avgQuery = "SELECT AVG(intervenciones) AS media_intervenciones FROM alumnos";
            ResultSet avgResultSet = avgStatement.executeQuery(avgQuery);
            avgResultSet.next();
            double media = avgResultSet.getDouble("media_intervenciones");
            avgResultSet.close();
            avgStatement.close();


            // Seleccionar los alumnos con menos de la media de participaciones
            Statement statement = connection.createStatement();
            String query = "SELECT nombre, intervenciones FROM alumnos WHERE intervenciones < " + media;
            ResultSet resultSet = statement.executeQuery(query);


            // Imprimir los alumnos seleccionados
            System.out.println("Alumnos con menos de la media de participaciones (" + media + "):");
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int intervenciones = resultSet.getInt("intervenciones");
                System.out.println("Nombre: " + nombre + ", Intervenciones: " + intervenciones);
            }


            // Cerrar el resultado y la declaración
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



