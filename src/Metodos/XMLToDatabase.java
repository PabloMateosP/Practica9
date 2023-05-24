package Metodos;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class XMLToDatabase {
    public static void XMLToDatabase (){

        System.out.println("Esta seguro que desea pasar los datos de su XML a la base de datos ?" +
                " \nSi es asi escriba si, por el contrario si no desea importar nada " +
                "\na la Base de Datos escriba cancelar");
        Scanner export0 = new Scanner(System.in);
        String rutaEntrar = export0.nextLine();

        System.out.println("Escriba la ruta de su base de datos: ");
        Scanner export1 = new Scanner(System.in);
        String rutaDatabase = export1.nextLine();

        System.out.println("Escriba la ruta de su archivo xml: ");
        Scanner export = new Scanner(System.in);
        String rutaFile = export.nextLine();

        if (rutaEntrar.equalsIgnoreCase("si")) {
            try {
                //TODO: Debemos de cambiar esto a tal y como lo hemos hecho en el tema 7
                SAXBuilder builder = new SAXBuilder();
                Document document = builder.build(rutaFile);
                Element rootElement = document.getRootElement();

                Connection connection = DriverManager.getConnection(rutaDatabase);

                String insertQuery = "INSERT INTO alumnos (nombre, intervenciones, fecha_intervencion) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                for (Element element : rootElement.getChildren()) {

                    String nombre = element.getChildText("nombre");
                    Integer intervenciones = Integer.parseUnsignedInt(element.getChildText("intervenciones"));
                    String fecha1 = element.getChildText("fecha");
                    Date fecha = Date.valueOf(fecha1);


                    insertStatement.setString(1, nombre);
                    insertStatement.setInt(2, intervenciones);
                    insertStatement.setDate(3, fecha);

                    insertStatement.executeUpdate();
                }

                // Cerrar recursos
                insertStatement.close();
                connection.close();

                System.out.println("Datos insertados correctamente en la base de datos.");
            } catch (IllegalArgumentException ae) {
                ae.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rutaEntrar.equalsIgnoreCase("cancelar")) {
            System.out.println("Las base de datos no ha sido modificado");
        }
    }

    public static void main(String[] args) {
        XMLToDatabase();
    }
}