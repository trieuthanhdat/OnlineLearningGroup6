package DAO.Quiz;

import DTO.Question.QuestionDTO;
import DTO.Question.QuizQuestionDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class QuizQuestionDAO implements Serializable {

    private List<QuizQuestionDTO> quizQuestion = null;
    private List<QuizQuestionDTO> newQuizQuestion = null;
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

    public boolean randomQuestion(List<QuestionDTO> questions, String level, int number, int quizID) {
        List<QuestionDTO> hard = new ArrayList();
        List<QuestionDTO> medium = new ArrayList();
        List<QuestionDTO> easy = new ArrayList();
        int hardNo = 0;
        int mediumNo = 0;
        int easyNo = 0;
        newQuizQuestion = new ArrayList();

        for (QuestionDTO question : questions) { //distinguish question level
            if (question.getLevel().equals("hard")) {
                hard.add(question);
            }
            if (question.getLevel().equals("medium")) {
                medium.add(question);
            }
            if (question.getLevel().equals("easy")) {
                easy.add(question);
            }
        }

        if (level.equals("hard")) { //quiz level = hard
            hardNo = (int) (number * 0.20);
            mediumNo = (int) (number * 0.35);
            easyNo = number - hardNo - mediumNo;

            if (hard.size() < hardNo || medium.size() < mediumNo || easy.size() < easyNo) {
                return false;
            }

            copyQuestion(hard, hardNo, quizID);
            copyQuestion(medium, mediumNo, quizID);
            copyQuestion(easy, easyNo, quizID);
            return true;

        }

        if (level.equals("medium")) { //quiz level = medium
            hardNo = (int) (number * 0.15);
            mediumNo = (int) (number * 0.4);
            easyNo = number - hardNo - mediumNo;

            if (hard.size() < hardNo || medium.size() < mediumNo || easy.size() < easyNo) {
                return false;
            }

            copyQuestion(hard, hardNo, quizID);
            copyQuestion(medium, mediumNo, quizID);
            copyQuestion(easy, easyNo, quizID);
            return true;
        }

        if (level.equals("easy")) { //quiz level = easy
            hardNo = (int) (number * 0.1);
            mediumNo = (int) (number * 0.35);
            easyNo = number - hardNo - mediumNo;

            if (hard.size() < hardNo || medium.size() < mediumNo || easy.size() < easyNo) {
                return false;
            }

            copyQuestion(hard, hardNo, quizID);
            copyQuestion(medium, mediumNo, quizID);
            copyQuestion(easy, easyNo, quizID);
            return true;
        }

        return false;
    }

    public void copyQuestion(List<QuestionDTO> question, int random, int quizID) { // copy question from list to quiz question
        Random generator = new Random();
        boolean ok;
        for (int i = 0; i < random; i++) {
            ok = true;
            int value = generator.nextInt(question.size());
            for (int j = 0; j < newQuizQuestion.size(); j++) {
                if (newQuizQuestion.get(j).getQuestionID() == question.get(value).getQuestionID()) {
                    i--;
                    ok = false;
                }
            }
            if (ok) {
                QuizQuestionDTO dto = new QuizQuestionDTO(0, quizID, question.get(value).getQuestionID(), question.get(value).getContent(), question.get(value).getExplanation(), question.get(value).getLevel(), question.get(value).getMediaLink(), true);
                newQuizQuestion.add(dto);
            }
        }
    }

    public boolean exportQuizQuestion() throws NamingException, SQLException, ClassNotFoundException { //insert into database
        boolean result = false;
        int row = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (int i = 0; i < newQuizQuestion.size(); i++) {
                    String sql = "INSERT INTO QuizQuestion(QuizID, QuestionID, Content, Explanation, Level, MediaLink, Status) "
                            + "VALUES (?,?,?,?,?,?,?) ";

                    stm = con.prepareStatement(sql);
                    stm.setInt(1, newQuizQuestion.get(i).getQuizID());
                    stm.setInt(2, newQuizQuestion.get(i).getQuestionID());
                    stm.setString(3, newQuizQuestion.get(i).getContent());
                    stm.setString(4, newQuizQuestion.get(i).getExplanation());
                    
                    stm.setString(5, newQuizQuestion.get(i).getLevel());
                    
                    stm.setString(6, newQuizQuestion.get(i).getMediaLink());
                    stm.setBoolean(7, false);

                    row += stm.executeUpdate();

                    if (row == newQuizQuestion.size()) {
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

    public void importQuizQuestion(int quizID) throws SQLException, NamingException, ClassNotFoundException { // take data from database
        quizQuestion = new ArrayList();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT QuestionNo, QuestionID, Content, Explanation, Level, MediaLink, Status "
                        + " FROM QuizQuestion "
                        + " WHERE QuizID = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int questionNo = rs.getInt("QuestionNo");
                    int questionID = rs.getInt("QuestionID");
                    String content = rs.getString("Content");
                    String explanation = rs.getString("Explanation");
                    String level = rs.getString("Level");
                    String mediaLink = rs.getString("MediaLink");
                    boolean status = rs.getBoolean("Status");
                    QuizQuestionDTO dto = new QuizQuestionDTO(questionNo, quizID, questionID, content, explanation, level, mediaLink, status);
                    quizQuestion.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    public boolean removeQuestionByNo(int questionNo) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM QuizQuestion "
                        + " WHERE QuestionNo = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, questionNo);
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

    public boolean removeQuestionByQuiz(int quizID) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM QuizQuestion "
                        + " WHERE QuizID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
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

    public boolean editQuizQuestion(QuizQuestionDTO question) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "UPDATE QuizQuestion SET Content=?, Explanation=?, "
                        + " MediaLink=? "
                        + " WHERE QuestionNo=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, question.getContent());
                stm.setString(2, question.getExplanation());
                stm.setString(3, question.getMediaLink());
                stm.setInt(4, question.getQuestionNo());

                if (stm.executeUpdate() > 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean editQuizQuestionStatus (int questionNo, boolean status) throws NamingException, SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            
            if (con != null) {
                String sql = "UPDATE QuizQuestion SET Status=? "
                        + " WHERE QuestionNo = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, questionNo);
                
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

    public List<QuizQuestionDTO> getQuizQuestionList() {
        return quizQuestion;
    }
}
