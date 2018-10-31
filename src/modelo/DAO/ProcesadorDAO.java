/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.VO.ProcesadorVO;
import modelo.VO.VO;

/**
 *
 * @author mdfda
 */
public class ProcesadorDAO extends DAO{
    public ProcesadorDAO(){
        super();
        super.setTabla("procesadores");
    }
    
    @Override
    public VO getRow(ResultSet r){
        ProcesadorVO procesador = null;
        try {
            //Instancia un nuevo objeto de tipo VO
            procesador = new ProcesadorVO( r.getInt("id"), r.getInt("precios"), r.getString("nombre"));
        } catch (SQLException ex) {
            Logger.getLogger(MonitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return procesador;
    }
}
