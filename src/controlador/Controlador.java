/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import modelo.DAO.MonitorDAO;
import modelo.DAO.PerifericoDAO;
import modelo.DAO.ProcesadorDAO;
import modelo.DAO.RamDAO;
import modelo.VO.VO;

/**
 *
 * @author mdfda
 */
public class Controlador {
    private final static ArrayList<ArrayList<VO>> DATOS = new ArrayList<>();
    private final static int MICROS = 0, RAM = 1, MONITOR = 2, EXTRAS = 3; //Indices de su posicion en el ArrayList (los usaré en la vista)
    
    //Método que invoca al método getAll() de las diferentes clases DAO para recuperar todos los valores de las tablas
    public static ArrayList<ArrayList<VO>> cargarDatos(){
        //recupero los registros de las 4 tablas y los añado al ArrayList
        ProcesadorDAO pD = new ProcesadorDAO();
        DATOS.add(MICROS, pD.getAll());
        RamDAO rD = new RamDAO();
        DATOS.add(RAM, rD.getAll());
        MonitorDAO mD = new MonitorDAO();
        DATOS.add(MONITOR, mD.getAll());
        PerifericoDAO peD = new PerifericoDAO();
        DATOS.add(EXTRAS, peD.getAll());
        return DATOS;
    }

    public static int getMICROS() {
        return MICROS;
    }

    public static int getRAM() {
        return RAM;
    }

    public static int getMONITOR() {
        return MONITOR;
    }

    public static int getEXTRAS() {
        return EXTRAS;
    }
}
