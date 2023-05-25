package Metodos;

import java.sql.*;
import java.util.Scanner;

public class IntroducirAlumno {
    //TODO: tenemos que crear un método con el cual demos al profesor la posibilidad de añadir un nuevo alumno
    // modificar un alumno existente (tanto nombre, intervenciones, fecha_intervencion) y borrar un alumno ya creado

    public static void IntroducirAlumno() {

        try {
            System.out.println("Escriba la ruta de su base de datos:");
            Scanner ruta = new Scanner(System.in);
            String rutaDatabase = ruta.nextLine();

            Connection connection = DriverManager.getConnection(rutaDatabase);


            System.out.println("Escriba el nombre del alumno: ");
            Scanner export1 = new Scanner(System.in);
            String nombre = export1.nextLine();


            System.out.println("Introduzca el numero de intervenciones");
            Scanner export2 = new Scanner(System.in);
            String  intervenciones = export2.nextLine();


            String sql = "INSERT INTO alumnos (nombre, intervenciones) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);


            pstmt.setString(1, nombre);
            pstmt.setInt(2, Integer.parseInt(intervenciones));


            pstmt.executeUpdate();
            System.out.println("Alumno introducido con exito a la base de datos.");
            System.out.println(" ");
            pstmt.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


