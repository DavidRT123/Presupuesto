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
import modelo.VO.PerifericoVO;
import modelo.VO.VO;

/**
 *
 * @author mdfda
 */
public class PerifericoDAO extends DAO{
    public PerifericoDAO(){
        super();
        super.setTabla("perifericos");
    }
    
    @Override
    public VO getRow(ResultSet r){
        PerifericoVO periferico = null;
        try {
            //Instancia un nuevo objeto de tipo VO
            periferico = new PerifericoVO( r.getInt("id"), r.getInt("precios"), r.getString("descripcion"));
        } catch (SQLException ex) {
            Logger.getLogger(MonitorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return periferico;
    }
}
