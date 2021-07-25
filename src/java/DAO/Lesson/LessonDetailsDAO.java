/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Lesson;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author ADMIN
 */
public class LessonDetailsDAO implements Serializable{

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
}
