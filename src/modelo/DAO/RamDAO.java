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
import modelo.VO.RamVO;
import modelo.VO.VO;

/**
 *
 * @author mdfda
 */
public class RamDAO extends DAO{
    public RamDAO(){
        super();
        super.setTabla("ram");
    }
    
    @Override
    public VO getRow(ResultSet r){
        //Instancia un nuevo objeto de tipo VO
        RamVO ram = null;
        try {
            ram = new RamVO( r.getInt("id"), r.getInt("precios"), r.getString("capacidad"));
        } catch (SQLException ex) {
            Logger.getLogger(MonitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ram;
    }
}
