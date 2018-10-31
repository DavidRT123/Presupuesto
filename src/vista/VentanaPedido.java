/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import modelo.VO.VO;
/**
 *
 * @author mdfda
 */
public class VentanaPedido extends JFrame implements ActionListener{
    private JPanel encuadre, micros, ram, monitores, extras;
    private JButton imprimir;
    private ArrayList<ArrayList<VO>> coleccion;//ArrayList que guarda los ArrayList<VO> con todos los registros de las tablas
    private int totalPagar = 0;
    
    public VentanaPedido(ArrayList<ArrayList<VO>> coleccion){
        //Setear variable colección con los datos de las tablas
        this.coleccion = coleccion;
        
        //Inicializar paneles
        encuadre = new JPanel();
        micros = new JPanel();
        ram = new JPanel();
        monitores = new JPanel();
        extras = new JPanel();
        
        //Asignar propiedades básicas de la ventana
        setLayout(new BorderLayout());
        setTitle("Elección de ordenador");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Asignar disposición al panel que contendrá a todos los subpaneles
        encuadre.setLayout(new GridLayout(1,4));
        
        //Asignar disposición a todos los subpaneles (disposición vertical)
        micros.setLayout(new BoxLayout(micros, BoxLayout.Y_AXIS));
        ram.setLayout(new BoxLayout(ram, BoxLayout.Y_AXIS));
        monitores.setLayout(new BoxLayout(monitores, BoxLayout.Y_AXIS));
        extras.setLayout(new BoxLayout(extras, BoxLayout.Y_AXIS));
        
        //Crear y asignar bordes con título
        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder("Procesadores");
        micros.setBorder(titulo);
        titulo = BorderFactory.createTitledBorder("RAM");
        ram.setBorder(titulo);
        titulo = BorderFactory.createTitledBorder("Monitores");
        monitores.setBorder(titulo);
        titulo = BorderFactory.createTitledBorder("Periféricos");
        extras.setBorder(titulo);
        
        //Extraer los datos y colocarlos en ButtonGroup y RadioButton (los indices son variables del controlador para que, de este modo, no se requiera conocer la estructura del ArrayList)
        rellenarPanel(micros, Controlador.getMICROS(), "radio");
        rellenarPanel(ram, Controlador.getRAM(), "radio");
        rellenarPanel(monitores, Controlador.getMONITOR(), "radio");
        rellenarPanel(extras, Controlador.getEXTRAS(), "check");
        
        //Añadir los paneles al panel principal
        encuadre.add(micros);
        encuadre.add(ram);
        encuadre.add(monitores);
        encuadre.add(extras);
        
        //Inicializar botón y añadir escuchador
        imprimir = new JButton("Imprimir");
        //Dado que la clases implementa la interfaz ActionListener le paso a ella misma (todo muy Android sí :) )
        imprimir.addActionListener(this);
        
        //Añadir panel principal y botón imprimir a la ventana
        add(encuadre, BorderLayout.CENTER);
        add(imprimir, BorderLayout.SOUTH);
    }
    
    //Método auxiliar para rellenar (crear UI) de los radios/checks en función del tipo
    private void rellenarPanel(JPanel panel, int indice, String tipo){
        //En función del tipo llama a uno o a otro
        switch(tipo){
            case "radio":
                rellenarRadio(panel, indice);
                break;
            case "check":
                rellenarCheck(panel, indice);
                break;
        }
    }
    
    //Método auxiliar  para rellenar (crear UI) de los radios
    private void rellenarRadio(JPanel panel, int indice){
        int i;
        ButtonGroup bg = new ButtonGroup();
        JRadioButton radio;
        for(i = 0 ; i < coleccion.get(indice).size() ; i++){
            radio = new JRadioButton(coleccion.get(indice).get(i).getNombre(), false);
            bg.add(radio);
            panel.add(radio);
        }
    }
    
    //Método auxiliar para rellenar (crear UI) de los checks
    private void rellenarCheck(JPanel panel, int indice){
        int i;
        JCheckBox check;
        
        for(i = 0 ; i < coleccion.get(indice).size() ; i++){
            check = new JCheckBox(coleccion.get(indice).get(i).getNombre());
            panel.add(check);
        }
    }
    
    //Método auxiliar para obtener las cadenas de los radios/checks
    private String getSelected(JPanel panel, int tabla, String tipo){
        if(tipo.equals("radio")){
            return getRadio(panel, tabla);
        }else{
            return getCheck(panel, tabla);
        }
    }
    
    //Método auxiliar para devolver los datos (en cadena) de los radios
    private String getRadio(JPanel panel, int tabla){
        int i;
        JRadioButton radio;
        
        //Bucar el componente que este seleccionado y obtener su nombre
        for (i = 0; i < panel.getComponentCount(); i++) {
            radio = (JRadioButton) panel.getComponent(i);
            //Encuentra el índice del radio que ha sido seleccionado
            if(radio.isSelected()){
                //Para evitar la correspondencia entre los índices del ArrayList y el iterador del for recorro todo el ArrayList hasta hayar una coincidencia
                ////////////////////////////////////////////////////////////////////////////
                //----------------> ESTO ES LO QUE ME COSTÓ TANTO DE RESOLVER (foreach)/////
                ////////////////////////////////////////////////////////////////////////////
                for(VO elemento: coleccion.get(tabla)){
                    if(elemento.getNombre() == radio.getText()){
                        totalPagar = totalPagar + elemento.getPrecio();
                        return radio.getText() + " | " + elemento.getPrecio() + "€";
                    }
                }
            }
        }
        return "Sin selección.";
    }
    
    //Método auxiliar para devolver los datos (en cadena) de los checks
    private String getCheck(JPanel panel, int tabla){
        int i;
        JCheckBox check;
        String extras = "";
        
        //Bucar el componente que este seleccionado y obtener su nombre
        for (i = 0; i < panel.getComponentCount(); i++) {
            check = (JCheckBox) panel.getComponent(i);
            if(check.isSelected()){
                //Para evitar la correspondencia entre los índices del ArrayList y el iterador del for recorro todo el ArrayList hasta hayar una coincidencia
                ////////////////////////////////////////////////////////////////////////////
                //----------------> ESTO ES LO QUE ME COSTÓ TANTO DE RESOLVER (foreach)/////
                ////////////////////////////////////////////////////////////////////////////
                for(VO elemento: coleccion.get(tabla)){
                    if(elemento.getNombre() == check.getText()){
                        totalPagar = totalPagar + elemento.getPrecio();
                        extras = extras + "\n ->" + check.getText() + " | " + coleccion.get(tabla).get(i).getPrecio() + "€";
                    }
                }
            }
        }
        if(extras.length() <= 0){
            return "Sin selección.";
        }
        return extras;
    }

    //Método del escuchador
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == imprimir){
            JTextArea texto;
            Boolean ok;
            String microSelet, ramSelet, monitorSelet, extrasSelet;
            
            //Llamar al método getSeleted() que en función de si es radio/check devolverá el valor correspondiente si esta o no seleccionado
            microSelet = getSelected(micros, Controlador.getMICROS(), "radio");
            ramSelet = getSelected(ram, Controlador.getRAM(), "radio");
            monitorSelet = getSelected(monitores, Controlador.getMONITOR(),"radio");
            extrasSelet = getSelected(extras, Controlador.getEXTRAS(),"check");
            
            //Realizar impresión
            try{
                texto = new JTextArea();
                texto.append("Procesador: " + microSelet+ "\n");
                texto.append("RAM: " + ramSelet+ "\n");
                texto.append("Monitor: " + monitorSelet+ "\n");
                texto.append("Extra/s: " + extrasSelet+ "\n");
                texto.append("--------------------------------------------------\n");
                texto.append("TOTAL A PAGAR: " + totalPagar + "€");
                        
                ok = texto.print();
                if(ok){
                    System.out.println("Operación realizada con éxito");
                }else{
                    System.out.println("Operación fallida");
                }
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
            
            //Resetear valor del total en caso de que se quiera volver a realizar nuevos pedidos
            totalPagar = 0;
        }
    }
}
