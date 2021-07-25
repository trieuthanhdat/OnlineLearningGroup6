package Servlet.Quiz;

import Error.QuizErrorDTO;
import DAO.Quiz.QuizOptionDAO;
import DAO.Answer.UserAnswerDAO;
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
@WebServlet(name = "UpdateQuizOptionServlet", urlPatterns = {"/UpdateQuizOptionServlet"})
public class UpdateQuizOptionServlet extends HttpServlet {
    private static final String QUIZ_QUESTION_PAGE = "QuizQuestion";

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
        
        String url = QUIZ_QUESTION_PAGE;
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int questionNo = Integer.parseInt(request.getParameter("questionNo"));
        String content = request.getParameter("content");
        boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect"));
        
        QuizOptionDAO option = new QuizOptionDAO();
        UserAnswerDAO answer = new UserAnswerDAO();
        QuizErrorDTO error = new QuizErrorDTO();
        
        try {
            if (answer.checkQuiz(quizID)) {
                throw new NamingException("Fuck you something is missing");
//                option.editOptionByQuestionNo(questionNo, content, isCorrect);
            } else {
                error.setFailToChangeError("Quiz have been done by someone, you can't do anything");
                request.setAttribute("UPDATE_OPTION_ERROR", error);
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
            Logger.getLogger(UpdateQuizOptionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuizOptionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuizOptionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
