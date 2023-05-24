//package Pruebas;
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class CrearBaseDatosCSV {
//
//    public static void main(String[] args) {
//        // Ruta del archivo csv
//        String csvFile = "libros.csv";
//        // Separador de los campos del csv
//        char separator = ',';
//        // Conexión a la base de datos
//        Connection con = null;
//        // Sentencia SQL
//        Statement stmt = null;
//        // Consulta preparada
//        PreparedStatement pstmt = null;
//        // Lector de csv
//        CSVReader reader = null;
//
//        try {
//            // Cargar el driver de MySQL
//            Class.forName("com.mysql.jdbc.Driver");
//            // Establecer la conexión con la base de datos
//            String sURL = "jdbc:mysql://localhost:3306/";
//            con = DriverManager.getConnection(sURL, "root", "");
//            // Crear la sentencia
//            stmt = con.createStatement();
//            // Crear la base de datos lineadecodigo si no existe
//            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS lineadecodigo");
//            // Usar la base de datos lineadecodigo
//            stmt.executeUpdate("USE lineadecodigo");
//            // Crear la tabla libros si no existe
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS libros (id INT PRIMARY KEY, titulo VARCHAR(100), autor VARCHAR(100))");
//            // Preparar la consulta para insertar datos en la tabla libros
//            String sql = "INSERT INTO libros (id, titulo, autor) VALUES (?, ?, ?)";
//            pstmt = con.prepareStatement(sql);
//            // Crear el lector de csv
//            reader = new CSVReader(new FileReader(csvFile), separator);
//            // Leer el csv línea por línea
//            String[] nextLine;
//            while ((nextLine = reader.readNext()) != null) {
//                if (nextLine != null) {
//                    // Asignar los valores del csv a los parámetros de la consulta
//                    pstmt.setInt(1, Integer.parseInt(nextLine[0]));
//                    pstmt.setString(2, nextLine[1]);
//                    pstmt.setString(3, nextLine[2]);
//                    // Ejecutar la consulta
//                    pstmt.executeUpdate();
//                }
//            }
//            System.out.println("Base de datos creada y poblada con éxito.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // Cerrar los recursos abiertos
//                if (reader != null) {
//                    reader.close();
//                }
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
