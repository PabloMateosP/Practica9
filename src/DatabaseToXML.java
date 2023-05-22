import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DatabaseToXML {
    public static void main(String[] args) {
        try {
            // Paso 1: Conectarse a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_base_de_datos", "usuario", "contraseña");

            // Paso 2: Obtener los datos de la base de datos
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nombre_tabla");

            // Paso 3: Crear un documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Paso 4: Rellenar el documento XML con los datos
            Element rootElement = doc.createElement("datos");
            doc.appendChild(rootElement);
            while (rs.next()) {
                Element elemento = doc.createElement("elemento");
                rootElement.appendChild(elemento);

                // Agregar elementos y atributos según los datos de la base de datos
                Element campo1 = doc.createElement("campo1");
                campo1.setTextContent(rs.getString("campo1"));
                elemento.appendChild(campo1);

                Element campo2 = doc.createElement("campo2");
                campo2.setTextContent(rs.getString("campo2"));
                elemento.appendChild(campo2);

                // Agregar más elementos y atributos según sea necesario
            }

            // Paso 5: Guardar el documento XML en un archivo
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.File("ruta/archivo.xml"));
            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente.");

            // Cerrar recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
