package DAO.Question;

import DTO.Question.QuizQuestionDTO;
import DTO.Quiz.QuizOptionDTO;
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
 * @author Admin
 */
public class QuizOptionDAO implements Serializable {

    private List<QuizOptionDTO> newQuizOption = null;
    private List<QuizOptionDTO> quizOption = null;
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

    public void getQuizOption(List<QuizQuestionDTO> question) throws SQLException, NamingException, ClassNotFoundException {
        newQuizOption = new ArrayList();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (QuizQuestionDTO quiz : question) {
                    String sql = "SELECT * FROM QuestionOption "
                            + " WHERE QuestionID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, quiz.getQuestionID());

                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String content = rs.getString("Content");
                        String optionLetter = rs.getString("OptionLetter");
                        boolean isCorrect = rs.getBoolean("isCorrect");
                        QuizOptionDTO dto = new QuizOptionDTO(quiz.getQuestionNo(), content, optionLetter, isCorrect);
                        newQuizOption.add(dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
    }

    public boolean exportOption() throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        int row = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (QuizOptionDTO option : newQuizOption) {
                    String sql = "INSERT INTO QuizQuestionOption(QuestionNo, OptionLetter, Content, isCorrect) "
                            + " VALUES(?,?,?,?) ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, option.getQuestionNo());
                    stm.setString(2, option.getOptionLetter());
                    stm.setString(3, option.getContent());
                    stm.setBoolean(4, option.isIsCorrect());
                    row += stm.executeUpdate();
                    if (row == newQuizOption.size()) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public void importOption(List<QuizQuestionDTO> questionList) throws NamingException, SQLException, ClassNotFoundException {
        quizOption = new ArrayList();
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (QuizQuestionDTO question : questionList) {
                    String sql = "SELECT * FROM QuizQuestionOption "
                            + " WHERE QuestionNo = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, question.getQuestionNo());

                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String content = rs.getString("Content");
                        String optionLetter = rs.getString("OptionLetter");
                        boolean isCorrect = rs.getBoolean("isCorrect");
                        QuizOptionDTO dto = new QuizOptionDTO(question.getQuestionNo(), content, optionLetter, isCorrect);
                        quizOption.add(dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
    }

    public boolean removeOption(List<QuizQuestionDTO> questions) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        int row = 0;
        try {
            con = DBHelpers.makeConnection();

            if (con != null) {
                for (QuizQuestionDTO items : questions) {
                    String sql = "DELETE FROM QuizQuestionOption "
                            + " WHERE QuestionNo = ? ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, items.getQuestionNo());
                    row += stm.executeUpdate();
                }

                if (row > 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean removeOptionByQuestionNo(int questionNo) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        int row = 0;
        try {
            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "DELETE FROM QuizQuestionOption "
                        + " WHERE QuestionNo = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, questionNo);
                row += stm.executeUpdate();
            }

            if (row > 0) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuizOptionDTO> getOption() {
        return quizOption;
    }

}
