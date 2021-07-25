package Temp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DTO.User.UserProfileDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class UserProfileDAO implements Serializable {

    private ArrayList<UserProfileDTO> userprofile = null;

    public ArrayList<UserProfileDTO> getUserProfileList() {
        return userprofile;
    }

    public UserProfileDAO() {
    }

    public void getUserProfile() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, Avatar, Gender, Mobile, Address "
                        + "FROM UserProfile ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String avatar = rs.getString("Avatar");
                    String gender = rs.getString("Gender");
                    String phone = rs.getString("Mobile");
                    String address = rs.getString("Address");

                    UserProfileDTO user = new UserProfileDTO(email, avatar, gender, phone, address);
                    if (this.userprofile == null) {
                        this.userprofile = new ArrayList<>();
                    }
                    this.userprofile.add(user);
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
    }

    public boolean createNewUserProfile(String Email, String Avatar, String Gender,
            String Mobile, String Address) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO "
                        + "UserProfile(Email, Avatar, Gender, Mobile, Address) "
                        + "VALUES(?, ?, ?, ?, ?) ";
                //3. Create statement and assign parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, Email);
                stm.setString(2, Avatar);
                stm.setString(3, Gender);
                stm.setString(4, Mobile);
                stm.setString(5, Address);                

                //4. Execute query
                int rowAffect = stm.executeUpdate();
                //5. Process result
                if (rowAffect > 0) {
                    return true;
                }
            } //end if con is opened
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

    public boolean updateUserProfile(
            String email,
            String avartar,
            String gender,
            String phone,
            String address
    ) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            String sql = "UPDATE UserProfile "
                    + "SET Avatar = ? , Gender = ? , Mobile = ? , Address = ? "
                    + "Where Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, avartar);
            stm.setString(2, gender);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, email);

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

    public boolean updateUserAvatar(String email, String avatar) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
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
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateUserProfile(
            String email,
            String gender,
            String phone,
            String address
    ) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
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
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public UserProfileDTO getUserProfileByEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserProfileDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, Avatar, Gender, Mobile, Address  "
                        + "FROM UserProfile "
                        + "WHERE Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String avatar = rs.getString("Avatar");
                    String gender = rs.getString("Gender");
                    String phone = rs.getString("Mobile");
                    String address = rs.getString("Address");
                    dto = new UserProfileDTO(email, avatar, gender, phone, address);
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
        return dto;
    }

}
