package Pruebas;

import Metodos.CrearDatabase;

import java.awt.*;
import java.awt.event.*;

public class MenuGUI extends Frame {
    public MenuGUI() {
        setTitle("Menú");
        setSize(400, 300);
        setLayout(new GridLayout(14, 1));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        Button btn1 = new Button("Crear Base de Datos");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción 1
                System.out.println("Has seleccionado opción 1");
                CrearDatabase.CrearDatabase();
            }
        });
        add(btn1);

        // Agrega los otros botones de acuerdo a las opciones restantes...

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuGUI();
    }
}
