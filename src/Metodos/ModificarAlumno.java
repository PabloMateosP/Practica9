package Metodos;

import java.sql.*;
import java.util.Scanner;

public class ModificarAlumno {
    //TODO: tenemos que crear un método con el cual demos al profesor la posibilidad de añadir un nuevo alumno
    // modificar un alumno existente (tanto nombre, intervenciones, fecha_intervencion) y borrar un alumno ya creado

    public static void IntroducirAlumno() {
        try {
            System.out.println("Escriba la ruta de su base de datos:");
            Scanner ruta = new Scanner(System.in);
            String rutaDatabase = ruta.nextLine();

            Connection connection = DriverManager.getConnection(rutaDatabase);


            System.out.println("Escriba el nombre del alumno a modificar: ");
            Scanner export1 = new Scanner(System.in);
            String nombre = export1.nextLine();

            System.out.println("Que desea modificar?" +
                    "\nSi desea modificar el nombre del alumno introduzca 'n', si por el contrario desea modificar las" +
                    "\nintervenciones introduzca 'i'");
            Scanner export0 = new Scanner(System.in);
            String rutaEntrar = export0.nextLine();
            if (rutaEntrar.equalsIgnoreCase("n")) {

                System.out.println("Escriba el nuevo nombre del alumno: ");
                Scanner export3 = new Scanner(System.in);
                String nombreNuevo = export1.nextLine();

                String sql = "UPDATE alumnos SET nombre = ? WHERE nombre = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, nombreNuevo);
                pstmt.setString(2, nombre);
                pstmt.executeUpdate();
                pstmt.close();

            } else if (rutaEntrar.equalsIgnoreCase("i")) {

                System.out.println("Introduzca el nuevo numero de intervenciones");
                Scanner export2 = new Scanner(System.in);
                String intervenciones = export2.nextLine();

                //UPDATE table_name
                //SET column1 = value1, column2 = value2, ...
                //WHERE condition;


                String sql2 = "UPDATE alumnos SET intervenciones = ? WHERE nombre = ?";
                PreparedStatement pstmt2 = connection.prepareStatement(sql2);
                pstmt2.setString(1, intervenciones);
                pstmt2.setString(2, nombre);
                pstmt2.executeUpdate();
                pstmt2.close();

            }

            System.out.println("Alumno actualizado con exito a la base de datos.");
            System.out.println(" ");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
