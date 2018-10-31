/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion_BD;
import modelo.VO.VO;

/**
 *
 * @author mdfda
 */
public abstract class DAO {

    private String tabla; //Hace que al sobreescribirla en las subclases las consultas se adecuen a la tabla correspondiente de la que reciben los datos
    private Connection conexion;
    private Statement s;
    private ResultSet r;

    public DAO(){
        //Iniciar la conexión con la que se llevarán a cabo las consultas
        conexion = Conexion_BD.abrirConexion();
    }
    
    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getS() {
        return s;
    }

    public void setS(Statement s) {
        this.s = s;
    }

    public ResultSet getR() {
        return r;
    }

    public void setR(ResultSet r) {
        this.r = r;
    }

    //Método para recibir todos los registros de una determinada tabla y guardarlos en un ArrayList<VO>
    public ArrayList<VO> getAll() {

        ArrayList<VO> coleccion = new ArrayList<>();

        try {

            s = conexion.createStatement();
            r = s.executeQuery("SELECT * FROM " + tabla);

            while (r.next()) {
                //Llamo al método getRow para que me cree el objetoDAO de la clase correspondiente y despues lo añado a la coleccion
                coleccion.add(getRow(r));
            }

            r.close();
            s.close();
            Conexion_BD.cerrarConexion(conexion);

        } catch (SQLException ex) {
            Logger.getLogger(MonitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return coleccion;

    }
    
    //Método a sobreescribir que variará la consulta que se realiza
    public abstract VO getRow(ResultSet r);
}
