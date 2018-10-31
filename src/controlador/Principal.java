/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.VentanaPedido;

/**
 *
 * @author mdfda
 */
public class Principal {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instanciar nueva ventana pasándole los datos de las tablas para que cree los menús dinámicamento con sus valores
        VentanaPedido v = new VentanaPedido(Controlador.cargarDatos());
        v.setVisible(true);
    }
}
