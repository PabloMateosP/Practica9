import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class XMLToDatabase {
    public static void main(String[] args) {
        try {
            // Paso 1: Leer el archivo XML
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build("src/daw1.xml");
            //Habría que meter la ruta mediante la interfaz gráfica o con un scanner
            Element rootElement = document.getRootElement();

            // Paso 2: Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/intervenciones");


            // Paso 3: Insertar los datos en la tabla
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
        } catch (IllegalArgumentException ae){
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}