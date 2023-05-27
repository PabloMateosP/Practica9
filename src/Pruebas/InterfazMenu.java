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
        ventaPrincipal.setSize(400, 300);
        ventaPrincipal.setLayout(new GridLayout(3,2,10,10));
        ventaPrincipal.setVisible(true);
        ventaPrincipal.setBackground(new Color(0xBABA96));

        //Texto bienvenido
        Label textoBienvenida = new Label("Bienvenido al programa para la modificacion de una base de datos");
        textoBienvenida.setVisible(true);
        textoBienvenida.setBounds(150, 50, 400, 25);
        textoBienvenida.setBackground(new Color(0xBABA96));
        textoBienvenida.setForeground(new Color(000000));
        ventaPrincipal.add(textoBienvenida);

        Label textoAclaracion = new Label("");
        textoBienvenida.setVisible(true);
        textoBienvenida.setBounds(150, 50, 400, 25);
        textoBienvenida.setBackground(new Color(0xBABA96));
        textoBienvenida.setForeground(new Color(000000));
        ventaPrincipal.add(textoBienvenida);


        //Botón borrar Base de datos
        Button botonBorraBase = new Button("Borrar Base");
        botonBorraBase.setBounds(250, 110, 100, 50);
        botonBorraBase.setVisible(true);
        botonBorraBase.setForeground(Color.BLACK);
        ventaPrincipal.add(botonBorraBase);

        //FRAME PARA BORRAR LA BASE DE DATOS
        Frame frameBorrarBase = new Frame("Borrar Base");
        frameBorrarBase.setSize(200, 200);
        frameBorrarBase.setLayout(new GridLayout(3,3,10,10));
        Label labeltextoRutaBase = new Label("Escriba la ruta de su Base:");
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

        botonBorraBase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frameBorrarBase.setVisible(true);
                labeltextoNombreBase.setVisible(true);
                textoBorrarBase.setVisible(true);
                textoNombreBase.setVisible(true);
                labeltextoRutaBase.setVisible(true);
                labeltextoRutaBase.setVisible(true);

                Button aceptar1 = new Button("Aceptar");
                aceptar1.setBounds(150, 300, 50, 50);
                frameBorrarBase.add(aceptar1);
                aceptar1.setVisible(true);

                //PARA QUE ESTE LISTENER SE ACTIVE DEBEMOS DE PULSAR ENTER DESPUÉS DE
                //INTRODUCIR EL TEXTO
                textoNombreBase.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String rutaBase = textoBorrarBase.getText();
                        String nombreBase = textoNombreBase.getText();
                        aceptar1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                BorrarDatabaseINTERFAZ.BorrarDatabase(rutaBase, nombreBase);
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


        //FRAME PARA CREAR LA BASE DE DATOS
        Frame frameCreaBase = new Frame("Crear Base");
        frameCreaBase.setSize(200, 200);
        frameCreaBase.setLayout(new GridLayout(3,3,10,10));
        Label labeltexto2RutaBase = new Label("Escriba la ruta de su base:");
        labeltexto2RutaBase.setBounds(10, 20, 160, 100);
        labeltexto2RutaBase.setForeground(Color.BLACK);
        frameCreaBase.add(labeltexto2RutaBase);

        Label labeltexto2NombreBase = new Label("Escriba el nombre de su base:");
        labeltexto2NombreBase.setBounds(10, 100, 170, 100);
        labeltexto2NombreBase.setForeground(Color.BLACK);
        frameCreaBase.add(labeltexto2NombreBase);

        TextField texto2CrearBase = new TextField();
        texto2CrearBase.setBounds(175, 60, 100, 20);
        frameCreaBase.add(texto2CrearBase);

        TextField texto2NombreBase = new TextField();
        texto2NombreBase.setBounds(190, 140, 100, 20);
        frameCreaBase.add(texto2NombreBase);

        Button botonCreaBase = new Button("Crear Base");
        botonCreaBase.setBounds(350, 110, 100, 50);
        botonCreaBase.setVisible(true);
        botonCreaBase.setForeground(Color.BLACK);
        ventaPrincipal.add(botonCreaBase);
        botonCreaBase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frameCreaBase.setVisible(true);
                labeltexto2NombreBase.setVisible(true);
                texto2CrearBase.setVisible(true);
                texto2NombreBase.setVisible(true);
                labeltexto2RutaBase.setVisible(true);
                labeltexto2RutaBase.setVisible(true);

                Button aceptar2 = new Button("Aceptar");
                aceptar2.setBounds(150, 300, 50, 50);
                frameCreaBase.add(aceptar2);
                aceptar2.setVisible(true);

                texto2NombreBase.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String rutaBase = texto2CrearBase.getText();
                        String nombreBase = texto2NombreBase.getText();
                        //SI FUNCIONA
                        aceptar2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                CrearDatabaseINTERFAZ.CrearDatabase(rutaBase, nombreBase);
                            }
                        });
                    }

                });

                frameCreaBase.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frameCreaBase.dispose();
                    }
                });
            }
        });
        ventaPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventaPrincipal.dispose();
            }
        });

        MenuBar menuBar = new MenuBar();
        Menu opcionesMenu = new Menu("Opciones");
        MenuItem salirItem = new MenuItem("Salir");
        Menu opcionAyuda = new Menu("Ayuda");

        // Agregar los items al menú
        opcionesMenu.add(salirItem);
        menuBar.add(opcionAyuda);
        menuBar.add(opcionesMenu);

        ventaPrincipal.setMenuBar(menuBar);

        salirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }
}