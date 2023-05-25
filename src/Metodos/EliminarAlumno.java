package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarAlumno {

    public static void main(String[] args) {
        eliminarAlumno();
    }

    public static void eliminarAlumno() {
        System.out.println("Escriba la ruta de su base de datos:");
        Scanner ruta = new Scanner(System.in);
        String rutaDatabase = ruta.nextLine();


        System.out.println("Introduzca el nombre del alumno al que desea eliminar");
        Scanner elimnar = new Scanner(System.in);
        String nombre = elimnar.nextLine();
        try {

            Connection con = DriverManager.getConnection(rutaDatabase);
            // Preparar la consulta para eliminar el alumno de la tabla alumnos
            String sql = "DELETE FROM alumnos WHERE nombre = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nombre);

            pstmt.executeUpdate();

            System.out.println("Alumno eliminado con exito de la base de datos.");
            System.out.println(" ");
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


