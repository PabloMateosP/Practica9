package Metodos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CSVToDatabase {
    public static void main(String[] args) {
        String csvFile = "ruta_al_archivo.csv";
        String databaseUrl = "jdbc:mysql://localhost:3306/nombre_base_de_datos";

        try (Connection connection = DriverManager.getConnection(databaseUrl);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;
            String insertQuery = "INSERT INTO tabla (col1, col2, col3) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String col1 = data[0];
                String col2 = data[1];
                String col3 = data[2];

                insertStatement.setString(1, col1);
                insertStatement.setString(2, col2);
                insertStatement.setString(3, col3);
                insertStatement.executeUpdate();
            }

            System.out.println("Datos insertados correctamente en la base de datos.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
