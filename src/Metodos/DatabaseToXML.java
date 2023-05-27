package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DatabaseToXML {
    public static void DatabaseToXML() {
        System.out.println("Desea exportar su Base de Datos a un archivo XML?" +
                " \nSi es asi escriba si, por el contrario si no desea realizar esta accion escriba cancelar");
        Scanner export0 = new Scanner(System.in);
        String rutaEntrar = export0.nextLine();


        if (rutaEntrar.equalsIgnoreCase("si")){
            try {
                // Paso 1: Conectarse a la base de datos
                System.out.println("Escriba la ruta de su base de datos:");
                Scanner export1 = new Scanner(System.in);
                String rutaDatabase = export1.nextLine();

                System.out.println("Escriba la ruta donde se creara su archivo:");
                Scanner export2 = new Scanner(System.in);
                String rutaarchivo = export2.nextLine();

                System.out.println("Escriba el nombre de su archivo:");
                Scanner export3 = new Scanner(System.in);
                String nombrearchivo = export3.nextLine();

                Connection conn = DriverManager.getConnection(rutaDatabase);

                // Obtenemos los datos de la base de datos
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");

                // Creamos un documento XML
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();

                // Rellenamos el documento XML con los datos
                Element rootElement = doc.createElement("daw1");
                doc.appendChild(rootElement);
                while (rs.next()) {
                    Element elemento = doc.createElement("alumno");
                    rootElement.appendChild(elemento);

                    // Agregamos elementos y atributos según los datos de la base de datos
                    Element campo1 = doc.createElement("nombre");
                    campo1.setTextContent(rs.getString("nombre"));
                    elemento.appendChild(campo1);

                    Element campo2 = doc.createElement("intervenciones");
                    campo2.setTextContent(rs.getString("intervenciones"));
                    elemento.appendChild(campo2);

                    Element campo3 = doc.createElement("fecha");
                    campo3.setTextContent(rs.getString("fecha_intervencion"));
                    elemento.appendChild(campo3);

                }

                // Guardamos el documento XML en un archivo
                javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
                javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
                javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
                javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.File(rutaarchivo + "/" + nombrearchivo + ".xml"));
                transformer.transform(source, result);

                System.out.println("Archivo XML creado correctamente.");

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rutaEntrar.equalsIgnoreCase("cancelar")) {
            System.out.println("La base de datos no ha sido modificada.");
        }
    }
}
