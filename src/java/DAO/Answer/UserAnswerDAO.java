/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Answer;

import DTO.Answer.AnswerDTO;
import DTO.Answer.UserAnswerDTO;
import DTO.Quiz.QuizOptionDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class UserAnswerDAO implements Serializable {

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

    public boolean updateNewestAnswer(UserAnswerDTO dto, String answer, String userID) throws NamingException, SQLException, ClassNotFoundException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE UserAnswer SET UserAnswer=?, Score=? "
                        + " WHERE QuizID = ? AND UserID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, answer);
                stm.setDouble(2, dto.getScore());
                stm.setInt(3, dto.getQuizID());
                stm.setString(4, userID);

                int roweffect = stm.executeUpdate();
                if (roweffect > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public UserAnswerDTO checkUserAnswerExist(int quizID, String userID) throws NamingException, SQLException, ClassNotFoundException {
        UserAnswerDTO userDidQuiz = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT UserAnswer, Score FROM UserAnswer "
                        + " WHERE UserID = ? AND QuizID = ? ";
                stm = con.prepareStatement(sql);

                stm.setString(1, userID);
                stm.setInt(2, quizID);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String user_answer = rs.getString("UserAnswer");
                    ArrayList<AnswerDTO> list = new ArrayList<>();
                    String[] answer = user_answer.split(",");
                    for (String tmpAnswer : answer) {
                        if (!tmpAnswer.equals("0")) {
                            String[] tmp = tmpAnswer.split(":");
                            AnswerDTO dto = new AnswerDTO(tmp[1], 0, Integer.parseInt(tmp[0]));
                            list.add(dto);
                        }
                    }
                    double score = rs.getDouble("Score");
                    userDidQuiz = new UserAnswerDTO(userID, quizID, list, score);
                }
            }
        } finally {
            closeConnection();
        }
        return userDidQuiz;
    }

    public AnswerDTO copyAnswer(List<QuizOptionDTO> options, int questionNo, String answer, double point) {
        AnswerDTO dto = null;
        for (QuizOptionDTO option : options) {
            if (option.getQuestionNo() == questionNo) {
                if (option.getOptionLetter().equals(answer)) {
                    if (option.isIsCorrect()) {
                        dto = new AnswerDTO(answer, point, questionNo);
                    } else {
                        dto = new AnswerDTO(answer, 0, questionNo);
                    }
                }
            }
        }
        return dto;
    }

    public double compareAnswer(List<AnswerDTO> Answers) {
        double total = 0;
        for (AnswerDTO answer : Answers) {
            total += answer.getPoint();
        }
        return total;
    }

    public String createString(List<AnswerDTO> answers) {
        String result = null;
        for (AnswerDTO answer : answers) {
            result += "," + answer.getQuestionNo() + ":" + answer.getAnswer();
        }
        return result;
    }

    public boolean saveToServer(UserAnswerDTO dto, String answer) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO UserAnswer (QuizID, UserID, UserAnswer, Score) "
                        + " VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getQuizID());
                stm.setString(2, dto.getUserID());
                stm.setString(3, answer);
                stm.setDouble(4, dto.getScore());
                int row = stm.executeUpdate();

                if (row > 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkQuiz(int quizID) throws NamingException, ClassNotFoundException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + " FROM UserAnswer "
                        + " WHERE QuizID = ? ";
                stm = con.prepareStatement(sql);

                stm.setInt(1, quizID);
                rs = stm.executeQuery();

                if (rs.next()) {
                    return false;
                }
            }
        } finally {
            closeConnection();
        }
        return true;
    }

    public boolean updateNewestAnswer(UserAnswerDTO dto, String answer) throws NamingException, SQLException, ClassNotFoundException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE UserAnswer SET UserAnswer=?, Score=? "
                        + " WHERE QuizID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, answer);
                stm.setDouble(2, dto.getScore());
                stm.setInt(3, dto.getQuizID());
                rs = stm.executeQuery();

                if (rs.next()) {
                    return false;
                }
            }
        } finally {
            closeConnection();
        }
        return true;
    }

}
