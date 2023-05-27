import Metodos.*;
import Metodos.ElegirAlumnoAleatoriamente;
import Metodos.EliminarAlumno;
import Metodos.IntroducirAlumno;
import Metodos.ModificarAlumno;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args){

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){

            System.out.println("1. Crear Base de Datos");
            System.out.println("2. Borrar Base de Datos");
            System.out.println("3. Crear Tabla");
            System.out.println("4. Borrar Tabla");
            System.out.println("5. Importar de XML a una Base de Datos");
            System.out.println("6. Importar de CSV a una Base de Datos");
            System.out.println("7. Exportar de Base de Datos a XML");
            System.out.println("8. Exportar de Base de Datos a CSV");
            System.out.println("9. Seleccionar Alumno Aleatorio");
            System.out.println("10. Poner a 0 todas las intervenciones");
            System.out.println("11. Introducir un alumno");
            System.out.println("12. Eliminar un alumno");
            System.out.println("13. Actualizar un alumno");
            System.out.println("14. Salir");


            try {

                System.out.println("Escriba una de las opciones");
                opcion = sn.nextInt();


                switch(opcion) {
                    case 1:
                        System.out.println("Has seleccionado opcion 1");
                        CrearDatabase.CrearDatabase();
                        break;
                    case 2:
                        System.out.println("Has seleccionado opcion 2");
                        BorrarDatabase.BorrarDatabase();
                        break;
                    case 3:
                        System.out.println("Has seleccionado opcion 3");
                        CrearTablaDatabase.CrearTablaDatabase();
                        break;
                    case 4:
                        System.out.println("Has seleccionado opcion 4");
                        DropTableDatabase.DropTableDatabase();
                        break;
                    case 5:
                        System.out.println("Has seleccionado opcion 5" );
                        XMLToDatabasesinjdom.XMLToDatabasesinjdom();
                        break;
                    case 6:
                        System.out.println("Has seleccionado opcion 6" );
                        CSVToDatabase.CSVToDatabase();
                        break;
                    case 7:
                        System.out.println("Has seleccionado opcion 7" );
                        DatabaseToXML.DatabaseToXML();
                        break;
                    case 8:
                        System.out.println("Has seleccionado opcion 8" );
                        DatabaseToCSV.exportDataToCSV();
                        break;
                    case 9:
                        System.out.println("Has seleccionado opcion 9" );
                        ElegirAlumnoAleatoriamente.seleccionarAlumnoAleatorioActualizarFecha();
                        break;
                    case 10:
                        System.out.println("Has seleccionado opcion 10" );
                        ResetearIntervenciones.resetearIntervenciones();
                        break;
                    case 11:
                        System.out.println("Has seleccionado opcion 11" );
                        IntroducirAlumno.IntroducirAlumno();
                        break;
                    case 12:
                        System.out.println("Has seleccionado opcion 12" );
                        EliminarAlumno.eliminarAlumno();
                        break;
                    case 13:
                        System.out.println("Has seleccionado opcion 13" );
                        ModificarAlumno.IntroducirAlumno();
                        break;

                    case 14:
                        salir = true;
                        break;
                    default:
                        //TODO: REAJUSTAR NÚMEROS
                        System.out.println("Solo numeros entre 1 y 13");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe insertar un numero");
                sn.next();
            }
        }
    }
}
