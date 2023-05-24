import Metodos.*;
import Metodos.RandomAlumnoUpdater;

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
            System.out.println("5. Exportar de XML a una Base de Datos");
            System.out.println("6. Exportar de CSV a una Base de Datos");
            System.out.println("7. Exportar de Base de Datos a XML");
            System.out.println("8. Exportar de Base de Datos a CSV");
            System.out.println("9. Seleccionar Alumno Aleatorio");
            System.out.println("10. Poner a 0 todas las intervenciones");
            System.out.println("11. Salir");




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
                        // TODO: Insertar Método DATABASETOCSV
                        break;
                    case 9:
                        System.out.println("Has seleccionado opcion 9" );
                        RandomAlumnoUpdater.seleccionarAlumnoAleatorioActualizarFecha();
                        break;
                    case 10:
                        System.out.println("Has seleccionado opción 10" );
                        ResetearIntervenciones.resetearIntervenciones();
                        break;
                    case 11:
                        salir = true;
                        break;
                    default:
                        //TODO: REAJUSTAR NÚMEROS
                        System.out.println("Solo números entre 1 y 11");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe insertar un número");
                sn.next();
            }
        }
    }
}
