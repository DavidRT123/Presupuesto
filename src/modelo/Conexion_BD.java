/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdfda
 */
public class Conexion_BD {
    private static final String database = "componentes", url = "jdbc:mysql://localhost:3306/" + database, pass = "", user = "root", driver = "com.mysql.jdbc.Driver";
    
    public static Connection abrirConexion(){
                
        Connection con = null;
        
        try{
            //Asignar driver
            Class.forName(driver);
            
            //Iniciar conexion
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    public static void cerrarConexion(Connection con){
        if(con != null){
            try{
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
