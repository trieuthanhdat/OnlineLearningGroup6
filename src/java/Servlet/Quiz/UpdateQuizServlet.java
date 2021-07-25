/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Quiz;

import DAO.Quiz.QuizDAO;
import DTO.Quiz.QuizDTO;
import Error.QuizErrorDTO;
import DAO.Answer.UserAnswerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateQuizServlet", urlPatterns = {"/UpdateQuizServlet"})

public class UpdateQuizServlet extends HttpServlet {

    private final static String QUIZ_LIST_PAGE = "GetSubjectForUpdatingServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int subID =Integer.parseInt(request.getParameter("subjectID"));
        String url = QUIZ_LIST_PAGE + "?txtSubjectID=" + subID;

        int quizID = Integer.parseInt(request.getParameter("quizID"));
        String name = request.getParameter("name");
        int Duration = Integer.parseInt(request.getParameter("duration"));
        int numOfQuestions = Integer.parseInt(request.getParameter("numOfQuestions"));
        double passRate = Double.parseDouble(request.getParameter("passRate"));

        QuizErrorDTO error = new QuizErrorDTO();
        UserAnswerDAO answer = new UserAnswerDAO();

        try {
            boolean isError = true;
            if (name.length() < 5 || name.length() > 50) { // name qúa ngắn hay quá dài
                error.setInvalidQuizName("Name must be 5-50 characters");
                isError = false;
            }

            if (passRate > 80 || passRate < 50) {// pass rate vô lí
                error.setUnreasonablePassRate("Pass rate must not lower than 50% and higher than 80%");
                isError = false;
            }

            if (numOfQuestions > Duration) {
                error.setUnreasonableTime("Each question must have at least 1 minute");
                isError = false;
            }

            if (isError) {
                if (answer.checkQuiz(quizID)) {
                    QuizDAO dao = new QuizDAO();
                    QuizDTO quiz = new QuizDTO(quizID, 0, name, 0, Duration, passRate, "", true);
                    boolean result = dao.editQuiz(quiz);
                    if (result) {// gọi lại hàm hiện quiz
                        QuizDTO tmpquiz = dao.getQuiz(quizID);
                        request.setAttribute("QUIZ_LIST", tmpquiz);
                    }
                } else {
                    error.setFailToChangeError("Quiz have been done by someone, you can disable it only");
                    request.setAttribute("UPDATE_QUIZ_ERROR", error);
                }
            } else {
                request.setAttribute("UPDATE_QUIZ_ERROR", error);
            }

        } catch (NamingException ex) {
            Logger.getLogger(UpdateQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
