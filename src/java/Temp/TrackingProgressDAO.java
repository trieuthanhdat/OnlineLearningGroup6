package Temp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DTO.TrackingProgress.TrackingProgressDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author DELL
 */
public class TrackingProgressDAO implements Serializable {

    public TrackingProgressDAO() {
    }

    public boolean addnewTrackingProgress(int registrationID, int LessonID, Date Deadline, boolean Status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into "
                        + "TrackingProgress(RegistrationID,LessonID,DeadLine,Status) "
                        + "Values(?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, registrationID);
                stm.setInt(2, LessonID);
                stm.setDate(3, Deadline);
                stm.setBoolean(4, Status);
                int rowEffect = stm.executeUpdate();
                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
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
        return false;
    }

    public int numberOfFinishLessonQuiz(int registrationID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(LessonID) "
                        + "FROM TrackingProgress "
                        + "WHERE RegistrationID = ? AND Status ='True' ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, registrationID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int numoffinishlessonquiz = rs.getInt(1);
                    return numoffinishlessonquiz;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();;
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }


    public boolean updateProgressStatus(int regisID, int lessonid, boolean status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            String sql = "UPDATE TrackingProgress "
                    + "SET  Status = ?  "
                    + "Where RegistrationID = ? And LessonID= ? ";
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, regisID);
            stm.setInt(3, lessonid);
            int rowAffect = stm.executeUpdate();
            if (rowAffect > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<TrackingProgressDTO> getTrackingProgressByRegistrationID(int registrationID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        List<TrackingProgressDTO> trackList = new ArrayList<>();
        
        try {
             //1.Connect D
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT LessonID, DeadLine, Status "
                        + "FROM TrackingProgress "
                        + "WHERE RegistrationID = ? ";
                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setInt(1, registrationID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int lessonID = rs.getInt("LessonID");
                    Date deadline = rs.getDate("DeadLine");
                    boolean status = rs.getBoolean("Status");
                    TrackingProgressDTO dto = new TrackingProgressDTO(registrationID, lessonID, deadline, status, 0);
                    trackList.add(dto);
                }
            }//end if it is existed
            //end if connection is opened
        } finally {
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
        return trackList;
    }

    public TrackingProgressDTO getTrackingProgressByRegistrationIDandLessonID(int registrationID, int lessonID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.Connect D
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT LessonID, DeadLine, Status "
                        + "FROM TrackingProgress "
                        + "WHERE RegistrationID = ? AND LessonID = ?";
                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setInt(1, registrationID);
                stm.setInt(2, lessonID);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    Date deadline = rs.getDate("DeadLine");
                    boolean status = rs.getBoolean("Status");
                    TrackingProgressDTO dto = new TrackingProgressDTO(registrationID, lessonID, deadline, status, 0);
                    return dto;
                }
            }//end if it is existed
            //end if connection is opened
        } finally {
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
        return null;
    }

}
