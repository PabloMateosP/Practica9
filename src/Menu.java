import Metodos.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
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


            try {

                System.out.println("Escriba una de las opciones");
                opcion = sn.nextInt();

                switch(opcion) {
                    case 1:
                        System.out.println("Has seleccionado opción 1");
                        CrearDatabase.CrearDatabase();
                        break;
                    case 2:
                        System.out.println("Has seleccionado opción 2");
                        BorrarDatabase.BorrarDatabase();
                        break;
                    case 3:
                        System.out.println("Has seleccionado opción 3");
                        CrearTablaDatabase.CrearTablaDatabase();
                        break;
                    case 4:
                        System.out.println("Has seleccionado opción 4");
                        DropTableDatabase.DropTableDatabase();
                        break;
                    case 5:
                        System.out.println("Has seleccionado opción 5" );
                        XMLToDatabase.XMLToDatabase();
                        break;
                    case 6:
                        System.out.println("Has seleccionado opción 5" );
                        // TODO: Insertar Método CSVTODATABASE
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        //TODO: REAJUSTAR NÚMEROS
                        System.out.println("Solo números entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe insertar un número");
                sn.next();
            }
        }
    }
}
