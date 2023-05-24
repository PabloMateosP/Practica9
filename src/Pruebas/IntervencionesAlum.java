package Pruebas;

import Metodos.CrearTablaDatabase;

import java.sql.*;
import java.util.*;

public class IntervencionesAlum {
    public static void main(String[] args) {



        try  {
            System.out.println("Escriba la ruta de su base de datos:");
            Scanner export1 = new Scanner(System.in);
            String rutaDatabase = export1.nextLine();
            Connection connection = DriverManager.getConnection(rutaDatabase);

            List<String> alumnosMenosParticipaciones = obtenerAlumnosMenosParticipaciones(connection);
            List<String> alumnosMasParticipaciones = obtenerAlumnosMasParticipaciones(connection);

            System.out.println("Alumnos con menos participaciones:");
            for (String alumno : alumnosMenosParticipaciones) {
                System.out.println(alumno);
            }

            System.out.println("\nAlumnos con más participaciones:");
            for (String alumno : alumnosMasParticipaciones) {
                System.out.println(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> obtenerAlumnosMenosParticipaciones(Connection connection) {
        int minParticipaciones = obtenerMinParticipaciones(connection);
        return obtenerAlumnosPorParticipaciones(connection, minParticipaciones);
    }

    public static List<String> obtenerAlumnosMasParticipaciones(Connection connection) {
        int maxParticipaciones = obtenerMaxParticipaciones(connection);
        return obtenerAlumnosPorParticipaciones(connection, maxParticipaciones);
    }

    private static int obtenerMinParticipaciones(Connection connection){
        String query = "SELECT MIN(intervenciones) FROM alumnos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException sq){
            sq.printStackTrace();
        }
        return 0;
    }

    private static int obtenerMaxParticipaciones(Connection connection)
    {
        try {
             String query = "SELECT MAX(intervenciones) FROM alumnos";
                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(query)) {
                        if (resultSet.next()) {
                            return resultSet.getInt(1);
                        }
                    }
                           return 0;
        } catch (SQLException sq){
            sq.printStackTrace();
        }
        return 0;
    }

    private static List<String> obtenerAlumnosPorParticipaciones(Connection connection, int participaciones) {
        List <String> a = new ArrayList<>();
        try {
            List<String> alumnos = new ArrayList<>();
            String query = "SELECT nombre FROM alumnos WHERE intervenciones = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, participaciones);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    alumnos.add(resultSet.getString("nombre"));
                }
            }
            return alumnos;
        } catch (SQLException sq){
            sq.printStackTrace();
        }
        return a;
    }
}