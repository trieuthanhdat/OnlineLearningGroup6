package DAO.Subject;

import DTO.Subject.SubjectDTO;
import DTO.Subject.SubjectDetailsDTO;
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
import DTO.Marketing.StatisDTO;

/**
 *
 * @author ASUS
 */
public class SubjectDAO implements Serializable {

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

    public SubjectDAO()
            throws NamingException, SQLException {
        getAllSubjects();
    }

    private List<SubjectDTO> subjectsList = new ArrayList<>();

    public List<SubjectDTO> getSubjectsList() {
        return subjectsList;
    }

    public void getAllSubjects()
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT SubjectID, SubjectCategoryID, Title, NumOfLessons, FeatureFlag, "
                        + "Thumbnail, Owner, BriefInfo, Status "
                        + "FROM Subject";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int subjectID = rs.getInt("SubjectID");
                    int categoryID = rs.getInt("SubjectCategoryID");
                    String subjectTitle = rs.getNString("Title");
                    int numOfLessons = rs.getInt("NumOfLessons");
                    String thumbnail = rs.getString("Thumbnail");
                    String ownerID = rs.getString("Owner");
                    String briefInfo = rs.getNString("BriefInfo");
                    boolean status = rs.getBoolean("Status");
                    boolean flag = rs.getBoolean("FeatureFlag");
                    subjectsList.add(new SubjectDTO(subjectID, categoryID, subjectTitle, numOfLessons, thumbnail, ownerID, briefInfo, null, null, status, flag));
                }
            }
        } finally {
            closeConnection();
        }
    }

    public List<SubjectDTO> getSubjectByCategory(int categoryID)
            throws NamingException, SQLException {
        List<SubjectDTO> tmp = new ArrayList();
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + " FROM Subject "
                        + " WHERE SubjectCategoryID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int subjectID = rs.getInt("SubjectID");
                    String subjectTitle = rs.getNString("Title");
                    int numOfLessons = rs.getInt("NumOfLessons");
                    String thumbnail = rs.getString("Thumbnail");
                    String ownerID = rs.getString("Owner");
                    String briefInfo = rs.getNString("BriefInfo");
                    boolean status = rs.getBoolean("Status");
                    boolean flag = rs.getBoolean("FeatureFlag");
                    tmp.add(new SubjectDTO(subjectID, categoryID, subjectTitle, numOfLessons, thumbnail, ownerID, briefInfo, null, null, status, flag));
                }
            }
        } finally {
            closeConnection();
        }
        return tmp;
    }


    public boolean updateUserAvatar(String email, String avatar) throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            String sql = "UPDATE UserProfile "
                    + "SET Avatar = ? "
                    + "Where Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, avatar);
            stm.setString(2, email);

            int rowAffect = stm.executeUpdate();
            if (rowAffect > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateUserProfile(
            String email,
            String gender,
            String phone,
            String address
    ) throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            String sql = "UPDATE UserProfile "
                    + "SET   Gender = ? , Mobile = ? , Address = ? "
                    + "Where Email = ? ";
            stm = con.prepareStatement(sql);

            stm.setString(1, gender);
            stm.setString(2, phone);
            stm.setString(3, address);
            stm.setString(4, email);

            int rowAffect = stm.executeUpdate();
            if (rowAffect > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<SubjectDTO> getAvailableSubjectList(String userID, String userRole) {

        List<SubjectDTO> resultList = new ArrayList<>();

        if (userRole.toLowerCase().equals("admin")) {
            for (SubjectDTO sub : subjectsList) {
                resultList.add(sub);
            }
        } else {
            for (SubjectDTO sub : subjectsList) {
                if (sub.getOwnerID().equals(userID)) {
                    resultList.add(sub);
                }
            }
        }
        return resultList;

    }

    public boolean updateSubjectStatus(int subjectID)
            throws NamingException, SQLException {

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Subject "
                        + "SET Status = ? "
                        + "WHERE SubjectID = ?";

                stm = con.prepareStatement(sql);
                SubjectDTO dto = subjectsList.get(subjectID - 1); // subjectID - 1 = index of subject 
                if (dto == null) {
                    return false;
                } else {
                    boolean currStatus = dto.isStatus();
                    stm.setInt(1, currStatus ? 0 : 1);
                    stm.setInt(2, subjectID);

                    int updateResult = stm.executeUpdate();
                    if (updateResult == 1) {
                        return true;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public SubjectDTO getSubjectByID(int subjectID) {
        SubjectDTO resultSubject = null;
        for (SubjectDTO subItem : subjectsList) {
            if (subItem.getSubjectID() == subjectID) {
                resultSubject = subItem;
                break;
            }
        }
        return resultSubject;
    }

    public SubjectDetailsDTO getSubjectDetailsByID(int subjectID)
            throws NamingException, SQLException {
        SubjectDetailsDTO details = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT TagLine, Description "
                        + "FROM SubjectDetails "
                        + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectID);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String tagLine = rs.getString("TagLine");
                    String description = rs.getString("Description");

                    details = new SubjectDetailsDTO(tagLine, description);
                }
            }
        } finally {
            closeConnection();
        }
        return details;
    }

    public boolean addNewSubject(SubjectDTO newSubject)
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Subject (SubjectCategoryID, Title, "
                        + "NumOfLessons, Thumbnail, Owner, BriefInfo, Status, FeatureFlag) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, newSubject.getSubjectCategoryID());
                stm.setNString(2, newSubject.getTitle());
                stm.setInt(3, newSubject.getNumOfLessons());
                stm.setString(4, newSubject.getThumbnail());
                stm.setString(5, newSubject.getOwnerID());
                stm.setNString(6, newSubject.getBriefInfo());
                stm.setBoolean(7, newSubject.isStatus());
                stm.setBoolean(8, false);

                int result = stm.executeUpdate();
                if (result != 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean addNewSubjectDetails(SubjectDTO newSubject)
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO SubjectDetails (SubjectID, TagLine, Description) "
                        + "VALUES (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectsList.size() + 1);
                stm.setNString(2, newSubject.getDetails().getTagLine());
                stm.setNString(3, newSubject.getDetails().getDescription());

                int result = stm.executeUpdate();
                if (result != 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateSubject(SubjectDTO sub)
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            con.setAutoCommit(false);

            if (con != null) {
                String sql = "UPDATE Subject "
                        + "SET SubjectCategoryID = ?, Title = ?, Thumbnail = ?, BriefInfo = ?, FeatureFlag = ? "
                        + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, sub.getSubjectCategoryID());
                stm.setNString(2, sub.getTitle());
                stm.setString(3, sub.getThumbnail());
                stm.setNString(4, sub.getBriefInfo());
                stm.setBoolean(5, sub.isFeatureFlag());
                stm.setInt(6, sub.getSubjectID());

                int result1 = stm.executeUpdate();

                sql = "UPDATE SubjectDetails "
                        + "SET TagLine = ?, Description = ? "
                        + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, sub.getDetails().getTagLine());
                stm.setNString(2, sub.getDetails().getDescription());
                stm.setInt(3, sub.getSubjectID());

                int result2 = stm.executeUpdate();

                con.commit();

                if (result1 != 0 && result2 != 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            con.rollback();
            throw new SQLException(ex.toString());
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<SubjectDTO> searchCourse(String courseName, int categoryID) {
        List<SubjectDTO> resultList = new ArrayList<>();
        for (SubjectDTO sub : subjectsList) {
            if (categoryID == 0) {
                if (sub.getTitle().toLowerCase().contains(courseName.toLowerCase())) {
                    resultList.add(sub);
                }
            } else if (sub.getTitle().toLowerCase().contains(courseName.toLowerCase()) && sub.getSubjectCategoryID() == categoryID) {
                resultList.add(sub);
            }
        }
        return resultList;
    }

    public int getNumberOfSubject() throws NamingException, SQLException {
        int num = 0;
        try {
            //1.Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select COUNT (SubjectID) "
                        + "From Subjects ";

                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                if (rs.next()) {
                    num = rs.getInt(1);
                }
            }//end if it is existed
            //end if connection is opened

        } finally {
            closeConnection();
        }
        return num;
    }

    public ArrayList<StatisDTO> getTop5PopularSubject(Date from, Date to) throws NamingException, SQLException {
        ArrayList<StatisDTO> list = null;

        try {
            //1.Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select TOP 5 SubjectID,  COUNT(RegistrationID) AS 'RegistrationTime' "
                        + "From Registration "
                        + "Where RegistrationTime Between ? and ? "
                        + "Group By SubjectID "
                        + "ORDER BY  'RegistrationTime'  DESC   ,SubjectID ";
                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setDate(1, from);
                stm.setDate(2, to);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    int registrationID = rs.getInt("SubjectID");
                    int registrationTime = rs.getInt("RegistrationTime");
                    StatisDTO dto = new StatisDTO(0, 0, 0, registrationID, registrationTime);
                    list.add(dto);
                }//end if it is existed
            }//end if connection is opened
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateSubjectFeatureFlag(int subjectID)
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Subject "
                        + "SET FeatureFlag = ? "
                        + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);

                SubjectDTO currSub = getSubjectByID(subjectID);
                stm.setBoolean(1, !currSub.isFeatureFlag());
                stm.setInt(2, subjectID);

                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getLatestSubID()
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT MAX(SubjectID) "
                        + "FROM Subject";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int latestID = rs.getInt(1);
                    return latestID;
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
}
