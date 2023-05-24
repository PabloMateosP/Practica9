package Metodos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CSVToDatabase {
    public static void CSVToDatabase() {
        String csvFile = "src/participaciones_20230518 - participaciones_20230518.csv";
        String databaseUrl = "jdbc:mariadb://localhost:3306/intervenciones";

        try (Connection connection = DriverManager.getConnection(databaseUrl);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;
            String insertQuery = "INSERT INTO alumnos (nombre, intervenciones, fecha_intervencion) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String nombre = data[0];
                String intervenciones = data[1];
                String fecha_intervencion = data[2];

                insertStatement.setString(1, nombre);
                insertStatement.setString(2, intervenciones);
                insertStatement.setString(3, fecha_intervencion);
                insertStatement.executeUpdate();
            }

            System.out.println("Datos insertados correctamente en la base de datos.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
