package Pruebas;

import Metodos.BorrarDatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfazMenu {
    public static void main(String[] args) {
        InterfazMenu a = new InterfazMenu();
    }

    private InterfazMenu() {
        //Creación frame para principal
        Frame ventaPrincipal = new Frame("Intervenciones Alumnos");

        //Creación barra menú para salir
        MenuBar barraMenu = new MenuBar();
        //Creación menú para salir
        Menu menu = new Menu("Salir");

        MenuItem item1 = new MenuItem("Salir");

        menu.add(item1);
        barraMenu.add(menu);
        ventaPrincipal.setMenuBar(barraMenu);
        ventaPrincipal.setSize(800, 800);
        ventaPrincipal.setLayout(null);
        ventaPrincipal.setVisible(true);
        ventaPrincipal.setBackground(new Color(0xBABA96));

        //Texto bienvenido
        Label textoBienvenida = new Label("Bienvenido al programa para la modificacion de una base de datos");
        textoBienvenida.setVisible(true);
        textoBienvenida.setBounds(150, 50, 400, 25);
        textoBienvenida.setBackground(new Color(0xBABA96));
        textoBienvenida.setForeground(new Color(000000));
        ventaPrincipal.add(textoBienvenida);

        //Creamos label para barra estética
        Label textoART = new Label("|------------------------------------------------------------------------------------------------|");
        textoART.setVisible(true);
        textoART.setBounds(142, 75, 400, 10);
        textoART.setBackground(new Color(0xBABA96));
        textoART.setForeground(new Color(000000));
        ventaPrincipal.add(textoART);

        //Botón borrar Base de datos
        Button botonBorraBase = new Button("Borrar Base");
        botonBorraBase.setBounds(50, 110, 100, 50);
        botonBorraBase.setVisible(true);
        botonBorraBase.setForeground(Color.BLACK);
        ventaPrincipal.add(botonBorraBase);

        //Frame borrarBase
        Frame frameBorrarBase = new Frame("Borrar Base");
        frameBorrarBase.setSize(400, 400);
        frameBorrarBase.setLayout(null);
        Label labeltextoRutaBase = new Label("Escriba la ruta de su archivo:");
        labeltextoRutaBase.setBounds(10, 20, 160, 100);
        labeltextoRutaBase.setForeground(Color.BLACK);
        frameBorrarBase.add(labeltextoRutaBase);


        Label labeltextoNombreBase = new Label("Escriba el nombre de su base:");
        labeltextoNombreBase.setBounds(10, 100, 170, 100);
        labeltextoNombreBase.setForeground(Color.BLACK);
        frameBorrarBase.add(labeltextoNombreBase);

        //Todo: introducir en los métodos una variable string la cual sea introducida mediante el Textfield como
        // por ejemplo la ruta en este caso de la base de datos.
        TextField textoBorrarBase = new TextField();
        textoBorrarBase.setBounds(175, 60, 100, 20);
        frameBorrarBase.add(textoBorrarBase);

        TextField textoNombreBase = new TextField();
        textoNombreBase.setBounds(190, 140, 100, 20);
        frameBorrarBase.add(textoNombreBase);

        Button aceptar = new Button("Aceptar");
        aceptar.setBounds(150, 300, 50, 50);
        frameBorrarBase.add(aceptar);



        botonBorraBase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frameBorrarBase.setVisible(true);
                labeltextoNombreBase.setVisible(true);
                textoBorrarBase.setVisible(true);
                textoNombreBase.setVisible(true);
                labeltextoRutaBase.setVisible(true);
                labeltextoRutaBase.setVisible(true);
                aceptar.setVisible(true);

                textoNombreBase.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String rutaBase = textoBorrarBase.getText();
                        String nombreBase = textoNombreBase.getText();
                        aceptar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                CrearDatabaseINTERFAZ.CrearDatabase(rutaBase, nombreBase);
                            }
                        });
                    }

                });

                frameBorrarBase.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frameBorrarBase.dispose();
                    }
                });
            }
        });
        //Cerrar frame borrar base mediante cruz roja

        ventaPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventaPrincipal.dispose();
            }
        });

    }
}