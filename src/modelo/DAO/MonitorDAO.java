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
import modelo.VO.MonitorVO;
import modelo.VO.VO;

/**
 *
 * @author mdfda
 */
public class MonitorDAO extends DAO{
    
    public MonitorDAO(){
        super();
        super.setTabla("monitores");
    }
    
    @Override
    public VO getRow(ResultSet r){
        MonitorVO monitor = null;
        try {
            //Instancia un nuevo objeto de tipo VO
            monitor = new MonitorVO( r.getInt("id"), r.getInt("precios"), r.getString("modelo"));
        } catch (SQLException ex) {
            Logger.getLogger(MonitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monitor;
    }
}
