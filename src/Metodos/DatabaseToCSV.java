package Metodos;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class DatabaseToCSV {
    public static void exportDataToCSV() {
        System.out.println("Desea exportar su Base de Datos a un archivo CSV?" +
                " \nSi es asi escriba si, por el contrario si no desea realizar esta accion escriba cancelar");
        Scanner export0 = new Scanner(System.in);
        String rutaEntrar = export0.nextLine();

        if (rutaEntrar.equalsIgnoreCase("si")){
            System.out.println("Escriba la ruta de su base de datos:");
            Scanner export1 = new Scanner(System.in);
            String rutaDatabase = export1.nextLine();

            System.out.println("Escriba el nombre de la tabla:");
            Scanner export2 = new Scanner(System.in);
            String nombreTabla = export1.nextLine();

            String query = "SELECT * FROM " + nombreTabla;

            System.out.println("Escriba la nueva ruta para el archivo nuevo:");
            Scanner export3 = new Scanner(System.in);
            String nombreArchivo = export1.nextLine();
            //TODO: añadir scanner nombre archivo y mirar porque solo crear 16 personas

            try {
                Connection connection = DriverManager.getConnection(rutaDatabase);
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(query);
                FileWriter fileWriter = new FileWriter(nombreArchivo);
                {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Escribir los nombres de columna en el archivo CSV
                    for (int i = 1; i <= columnCount; i++) {
                        fileWriter.append(metaData.getColumnName(i));
                        if (i < columnCount) {
                            fileWriter.append(",");
                        }
                    }
                    fileWriter.append("\n");


                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            Object value = resultSet.getObject(i);
                            fileWriter.append(String.valueOf(value));
                            if (i < columnCount) {
                                fileWriter.append(",");
                            }
                        }
                        fileWriter.append("\n");
                    }
                }
                fileWriter.close();
                System.out.println("Datos exportados exitosamente a CSV.");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();

            } catch (IOException io) {
                io.printStackTrace();
            }
        }else if (rutaEntrar.equalsIgnoreCase("cancelar")) {
            System.out.println("La base de datos no ha sido modificada.");
        }

    }
}
