package Metodos;
import javax.imageio.stream.ImageInputStreamImpl;
import java.sql.*;
import java.util.*;

public class RandomAlumnoUpdater {

    public static void seleccionarAlumnoAleatorioActualizarFecha(){
        try {

            System.out.println("Escriba la ruta de su base de datos:");
            Scanner export1 = new Scanner(System.in);
            String rutaDatabase = export1.nextLine();
            Connection connection = DriverManager.getConnection(rutaDatabase);

            int minIntervenciones = obtenerMinIntervenciones(connection);
            List<String> alumnos = obtenerAlumnos(connection, minIntervenciones);
            Random random = new Random();
            String alumnoSeleccionado = alumnos.get(random.nextInt(alumnos.size()));
            actualizarFechaIntervencion(connection, alumnoSeleccionado);

            mostrarInformacionAlumno(connection, alumnoSeleccionado);
            System.out.println("Fecha de intervencion actualizada correctamente.");

            //TODO: SE BUSCA AÑADIR, QUITAR O DEJAR IGUAL LAS INTERVENCIONES DEL ALUMNO
            Scanner scanner = new Scanner(System.in);
            System.out.println("La intervencion fue correcta? (Si/No)");
            String respuesta = scanner.nextLine();

            int intervencionesActuales = obtenerIntervencionesAlumno(connection, alumnoSeleccionado);
            int nuevasIntervenciones = intervencionesActuales;

            if (respuesta.equalsIgnoreCase("Si")) {
                nuevasIntervenciones++;
                System.out.println("Le ha sido sumada una intervencion ");
            } else if (respuesta.equalsIgnoreCase("No")) {
                nuevasIntervenciones--;
                System.out.println("Le ha sido rectada una intervencion");
            } else {
                System.out.println("No se realizaron cambios en las intervenciones.");
                return;
            }

            // Actualizar la cantidad de intervenciones en la base de datos
            actualizarIntervencionesAlumno(connection, alumnoSeleccionado, nuevasIntervenciones);

        } catch (SQLException aq){
            aq.printStackTrace();
        }
    }

    private static int obtenerMinIntervenciones(Connection connection) {
        String query = "SELECT MIN(intervenciones) FROM alumnos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
        return 0;
    }

    private static List<String> obtenerAlumnos(Connection connection, int minIntervenciones){
        List<String> alumnos = new ArrayList<>();
        String query = "SELECT nombre FROM alumnos WHERE intervenciones = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, minIntervenciones);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                alumnos.add(resultSet.getString("nombre"));
            }
        } catch (SQLException sq){
            sq.printStackTrace();
        }
        return alumnos;
    }

    private static void actualizarFechaIntervencion(Connection connection, String alumnoSeleccionado){
        String query = "UPDATE alumnos SET fecha_intervencion = CURRENT_TIMESTAMP WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, alumnoSeleccionado);
            statement.executeUpdate();
        } catch (SQLException sq){
            sq.printStackTrace();
        }
    }
    private static int obtenerIntervencionesAlumno(Connection connection, String alumno) throws SQLException {
        String query = "SELECT intervenciones FROM alumnos WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, alumno);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("intervenciones");
            }
        }
        return 0;
    }

    // Actualizar la cantidad de intervenciones de un alumno en la base de datos
    private static void actualizarIntervencionesAlumno(Connection connection, String alumno, int nuevasIntervenciones) throws SQLException {
        String query = "UPDATE alumnos SET intervenciones = ? WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuevasIntervenciones);
            statement.setString(2, alumno);
            statement.executeUpdate();
        }
    }
    private static void mostrarInformacionAlumno(Connection connection, String alumnoSeleccionado) throws SQLException {
        String query = "SELECT * FROM alumnos WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, alumnoSeleccionado);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int intervenciones = resultSet.getInt("intervenciones");
                Timestamp fechaIntervencion = resultSet.getTimestamp("fecha_intervencion");

                System.out.println(" ");
                System.out.println("---------------------------------------");
                System.out.println("Informacion del alumno seleccionado:");
                System.out.println("Nombre: " + nombre);
                System.out.println("Intervenciones: " + intervenciones);
                System.out.println("Fecha de intervencion: " + fechaIntervencion);
            }
        }
    }
}
