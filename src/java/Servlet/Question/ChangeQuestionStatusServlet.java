/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Question;

import DAO.Quiz.QuizDAO;
import Error.QuizErrorDTO;
import DAO.Quiz.QuizQuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "ChangeQuestionStatusServlet", urlPatterns = {"/ChangeQuestionStatusServlet"})
public class ChangeQuestionStatusServlet extends HttpServlet {
    private final static String QUIZ_QUESTION_PAGE = "QuizQuestion";

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String url = QUIZ_QUESTION_PAGE;
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int questionNo = Integer.parseInt(request.getParameter("questionNo"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int numOfQuestions = Integer.parseInt(request.getParameter("numOfQuestions"));
        
        QuizQuestionDAO question = new QuizQuestionDAO();
        QuizDAO quiz = new QuizDAO();
        
        try {            
            boolean result = question.editQuizQuestionStatus(questionNo, status);            
            if (result) {
                quiz.changeQuizNumOfQuestion(quizID, numOfQuestions - 1);
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(ChangeQuestionStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChangeQuestionStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangeQuestionStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
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
