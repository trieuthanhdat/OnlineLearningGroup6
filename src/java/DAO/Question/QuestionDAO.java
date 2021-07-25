/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Question;

import DTO.Question.QuestionDTO;
import DTO.Question.QuestionOptionDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class QuestionDAO implements Serializable{
    
    private List<QuestionDTO> questions = null;
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
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
    
    public void importQuestion (int lessonID) throws NamingException, SQLException {
        questions = new ArrayList();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID, mediaLink, content, status, explanation, level "
                        + "FROM Question "
                        + "WHERE lessonID = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, lessonID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int questionID = rs.getInt("questionID");
                        String mediaLink = rs.getString("mediaLink");
                        String content = rs.getString("content");
                        String explanation = rs.getString("explanation");
                        String level = rs.getString("level");
                        QuestionDTO dto = new QuestionDTO(questionID, mediaLink, content, lessonID, level, explanation, status);
                        questions.add(dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<QuestionDTO> getQuestion() {
        return questions;
    }
    
    private int getLatestQuestionID() 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT MAX(QuestionID) "
                           + "FROM Question";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }
    
    public boolean addNewQuestion(QuestionDTO newQuestion) 
            throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Question (LessonID, Content, MediaLink, Level, Explanation, Status) "
                           + "VALUES (?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, newQuestion.getLessonID());
                stm.setNString(2, newQuestion.getContent());                
                stm.setNull(3, Types.VARCHAR);
                stm.setString(4, newQuestion.getLevel());
                stm.setNString(5, newQuestion.getExplanation());
                stm.setBoolean(6, newQuestion.isStatus());
                
                int rowAffect = stm.executeUpdate();
                if (rowAffect > 0) {
                    result = true;
                }
        
                int latestID = getLatestQuestionID();
                
                for (QuestionOptionDTO option : newQuestion.getOptions()) {
                    sql = "INSERT INTO QuestionOption (QuestionID, OptionLetter, Content, IsCorrect) "
                            + "VALUES (?, ?, ?, ?)";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, latestID);
                    stm.setString(2, option.getOptionLetter());
                    stm.setNString(3, option.getContent());
                    stm.setBoolean(4, option.isIsCorrect());
                    
                    rowAffect = stm.executeUpdate();
                    if (rowAffect > 0) {
                        result = true;
                    }
                } 
            }
        } finally {
            closeConnection();
        }
        return true;
    }
}
