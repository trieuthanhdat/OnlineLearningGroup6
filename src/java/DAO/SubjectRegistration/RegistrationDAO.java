package DAO.SubjectRegistration;

import DTO.SubjectRegistration.RegistrationDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class RegistrationDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

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

    public RegistrationDAO()
            throws NamingException, SQLException {
        getAllRegistrations();
        validateAllRegistrations();
    }

    private List<RegistrationDTO> registrationsList = new ArrayList<>();

    public List<RegistrationDTO> getRegistrationsList() {
        return registrationsList;
    }

    public int getNumberRegisSubject(int subjectid) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int num = 0;
        try {
            //1.Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select COUNT (RegistrationID) "
                        + "From Registration  "
                        + "Where SubjectID = ? ";

                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectid);
                rs = stm.executeQuery();
                if (rs.next()) {
                    num = rs.getInt(1);
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
        return num;
    }

    private void getAllRegistrations()
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT RegistrationID, SubjectID, Email, RegistrationTime, "
                        + "TotalCost, ValidFrom, ValidTo, PackageID, Status "
                        + "FROM Registration";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int registrationID = rs.getInt("RegistrationID");
                    int subjectID = rs.getInt("SubjectID");
                    String email = rs.getString("Email");
                    Date registrationTime = rs.getDate("RegistrationTime");
                    int totalCost = rs.getInt("TotalCost");
                    Date validFrom = rs.getDate("ValidFrom");
                    Date validTo = rs.getDate("ValidTo");
                    int packageID = rs.getInt("PackageID");
                    boolean status = rs.getBoolean("Status");

                    registrationsList.add(new RegistrationDTO(registrationID, subjectID, email, registrationTime, totalCost, validFrom, validTo, packageID, status));
                }
            }
        } finally {
            closeConnection();
        }
    }

    public int getRegistrationIDBySubIDandEmail(int subjectID, String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int registrationid = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT RegistrationID "
                        + " From Registration "
                        + "Where SubjectID = ?  AND Email =  ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectID);
                stm.setString(2, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    registrationid = rs.getInt("RegistrationID");
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
        return registrationid;
    }

    public int getPackageIDbyRegisIdSubIdUserId(int regisid, int subjectid) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int pakageid = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT PackageID "
                        + " From Registration "
                        + "Where RegistrationID = ? and SubjectID = ?   ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, regisid);
                stm.setInt(2, subjectid);

                rs = stm.executeQuery();
                while (rs.next()) {
                    pakageid = rs.getInt("PackageID");
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
        return pakageid;
    }

    public boolean checkOwnCourse(String Email, int subjectID) {
        for (RegistrationDTO reg : registrationsList) {
            if (reg.getEmail().equals(Email) && reg.getSubjectID() == subjectID) {
                if (reg.isStatus() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validateAllRegistrations()
            throws NamingException, SQLException {
        try {
            Date currDate = new Date(Instant.now().toEpochMilli());

            for (RegistrationDTO reg : registrationsList) {
                if (currDate.compareTo(reg.getValidFrom()) > 0
                        && currDate.compareTo(reg.getValidTo()) < 0) {
                    // status is supposed to be true but is false in database
                    if (!reg.isStatus()) {
                        changeRegistrationStatus(reg.getRegistrationID(), reg.isStatus());
                        reg.setStatus(!reg.isStatus());
                    }
                } else {
                    // status is supposed to be false but is true in database
                    if (reg.isStatus()) {
                        changeRegistrationStatus(reg.getRegistrationID(), reg.isStatus());
                        reg.setStatus(!reg.isStatus());
                    }
                }
            }
        } finally {
        }
    }

    public List<RegistrationDTO> getUserRegistrations(String currUserEmail) {

        List<RegistrationDTO> resultList = new ArrayList<>();
        for (RegistrationDTO regis : registrationsList) {
            if (regis.getEmail().equals(currUserEmail)) {
                resultList.add(regis);
            }
        }
        return resultList;

    }

    public boolean checkRegistration(String currUserEmail, int subjectID) {
        boolean checkResult = false;
        for (RegistrationDTO regItem : registrationsList) {
            if (regItem.getSubjectID() == subjectID && regItem.getEmail().equals(currUserEmail) && regItem.isStatus()) {
                checkResult = true;
            }
        }
        return checkResult;
    }

    public void changeRegistrationStatus(int registrationID, boolean currStatus)
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET Status = ? "
                        + "WHERE RegistrationID = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, !currStatus);
                stm.setInt(2, registrationID);

                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    public boolean addNewRegistration(RegistrationDTO reg)
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO "
                        + "Registration(Email, SubjectID, PackageID, ValidFrom, ValidTo, "
                        + "TotalCost, RegistrationTime, Status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, reg.getEmail());
                stm.setInt(2, reg.getSubjectID());
                stm.setInt(3, reg.getPackageID());
                stm.setDate(4, reg.getValidFrom());
                stm.setDate(5, reg.getValidTo());
                BigDecimal totalCost = BigDecimal.valueOf(Double.valueOf(String.valueOf(reg.getTotalCost())));
                stm.setBigDecimal(6, totalCost);
                stm.setDate(7, reg.getRegistrationTime());
                stm.setBoolean(8, reg.isStatus());

                int rowEffect = stm.executeUpdate();
                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getRegistrationIDBySubIDandUserID(int subjectID, String userid) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int registrationid = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT RegistrationID "
                        + " From Registration "
                        + "Where SubjectID = ?  and UserID =  ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectID);
                stm.setString(2, userid);
                rs = stm.executeQuery();
                while (rs.next()) {
                    registrationid = rs.getInt("RegistrationID");
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
        return registrationid;
    }

    public int getLessonIDbyQuizID(int quizID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int lessonid = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT LessonID "
                        + " From LessonDetails "
                        + "Where QuizID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    lessonid = rs.getInt("LessonID");
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
        return lessonid;
    }

    public int getPackageIDbyRegisIdSubIdUserId(int regisid, int subjectid, String userid) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int pakageid = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT PackageID "
                        + " From Registration "
                        + "Where RegistrationID = ? and SubjectID = ?  and UserID =  ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, regisid);
                stm.setInt(2, subjectid);
                stm.setString(3, userid);
                rs = stm.executeQuery();
                while (rs.next()) {
                    pakageid = rs.getInt("RegistrationID");
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
        return pakageid;
    }
}
