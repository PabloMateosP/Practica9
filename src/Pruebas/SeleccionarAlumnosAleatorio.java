package Pruebas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeleccionarAlumnosAleatorio {

    public static void main(String args[]) {

        // Establecer la conexión a la base de datos
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/intervenciones");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Seleccionar un alumno aleatorio
        Alumno alumno = selectRandomAlumno(connection);

        // Imprimir el alumno seleccionado
        if (alumno != null) {
            System.out.println("Alumno seleccionado: " + alumno.getNombre());
            System.out.println("Intervenciones: " + alumno.getIntervenciones());
        }

        // Cerrar la conexión a la base de datos
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Alumno selectRandomAlumno(Connection connection) {
        Alumno alumno = null;
        try {
            // Crear una declaración SQL para obtener los datos de los alumnos
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nombre, intervenciones FROM alumnos");

            // Almacenar los datos de los alumnos en una lista
            List<Alumno> alumnos = new ArrayList<>();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int intervenciones = resultSet.getInt("intervenciones");
                alumnos.add(new Alumno(nombre, intervenciones));
            }

            //TODO ELEGIR SEGÚN LA MENOR PUNTUACION ENTRE TODOS LOS ALUMNOS

            // Seleccionar un índice aleatorio
            Random random = new Random();
            int randomIndex = random.nextInt(alumnos.size());

            // Obtener el alumno en el índice aleatorio
            alumno = alumnos.get(randomIndex);

            // Cerrar el resultado y la declaración
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    public static class Alumno {
        private String nombre;
        private int intervenciones;

        public Alumno(String nombre, int intervenciones) {
            this.nombre = nombre;
            this.intervenciones = intervenciones;
        }

        public String getNombre() {
            return nombre;
        }

        public int getIntervenciones() {
            return intervenciones;
        }
    }
}


