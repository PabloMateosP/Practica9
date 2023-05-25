package Metodos;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Scanner;

public class XMLToDatabasesinjdom {

    public static void XMLToDatabasesinjdom() {
        System.out.println("Esta seguro de que desea pasar los datos de su XML a la base de datos?" +
                "\nSi es asi, escriba 'si'. Por el contrario, si no desea importar nada a la base de datos, " +
                "\nescriba 'cancelar'.");

        Scanner export0 = new Scanner(System.in);
        String rutaEntrar = export0.nextLine();

        if (rutaEntrar.equalsIgnoreCase("si")) {
            try {
                System.out.println("Escriba la ruta de su base de datos:");
                Scanner export1 = new Scanner(System.in);
                String rutaDatabase = export1.nextLine();

                System.out.println("Escriba la ruta de su archivo XML:");
                Scanner export2 = new Scanner(System.in);
                String rutaFile = export2.nextLine();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(rutaFile));
                document.getDocumentElement().normalize();

                Connection connection = DriverManager.getConnection(rutaDatabase);

                String insertQuery = "INSERT INTO alumnos (nombre, intervenciones, fecha_intervencion) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                NodeList nodeList = document.getElementsByTagName("alumno");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                        int intervenciones = Integer.parseInt(element.getElementsByTagName("intervenciones").item(0).getTextContent());
                        String fechaString = element.getElementsByTagName("fecha").item(0).getTextContent();
                        Date fecha = Date.valueOf(fechaString);

                        insertStatement.setString(1, nombre);
                        insertStatement.setInt(2, intervenciones);
                        insertStatement.setDate(3, fecha);

                        insertStatement.executeUpdate();
                    }
                }

                // Cerrar recursos
                insertStatement.close();
                connection.close();

                System.out.println(" ");

                System.out.println("Datos insertados correctamente en la base de datos.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rutaEntrar.equalsIgnoreCase("cancelar")) {
            System.out.println("La base de datos no ha sido modificada.");
        }
    }
}
