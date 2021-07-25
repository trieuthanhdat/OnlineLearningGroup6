package DAO.Slider;

import DTO.Slider.SliderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class SliderDAO implements Serializable {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    private void closeConnection() 
            throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    public SliderDAO() 
            throws NamingException, SQLException {
        getAllSliders();
    }
    
    private List<SliderDTO> sliderList = new ArrayList<>();
    
    private void getAllSliders() 
        throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT SliderID, Title, BackLink, Image, Notes, Status "
                           + "FROM Slider";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int sliderID = rs.getInt("SliderID");
                    String title = rs.getString("Title");
                    String backlink = rs.getString("BackLink");
                    String image = rs.getString("Image");
                    String notes = rs.getString("Notes");
                    boolean status = rs.getBoolean("Status");
                    
                    sliderList.add(new SliderDTO(sliderID, title, backlink, image, notes, status));
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<SliderDTO> getAvailableSliders() {
        List<SliderDTO> resultList = new ArrayList<>();
        
        for (SliderDTO slider : sliderList) {
            if (slider.isStatus()) {
                resultList.add(slider);
            }
        }
        
        return resultList;
    }
}
