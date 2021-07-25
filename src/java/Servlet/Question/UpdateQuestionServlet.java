package Servlet.Question;

import Error.QuizErrorDTO;
import DAO.Quiz.QuizQuestionDAO;
import DTO.Question.QuizQuestionDTO;
import DAO.Answer.UserAnswerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class UpdateQuestionServlet extends HttpServlet {

    private static final String QUIZ_QUESTION_LIST = "QuizQuestionList";

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

        String url = QUIZ_QUESTION_LIST;

        int questionNo = Integer.parseInt(request.getParameter("questionNo"));
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        String content = request.getParameter("content");
        String mediaLink = request.getParameter("mediaLink");
        String explanation = request.getParameter("explanation");

        UserAnswerDAO answer = new UserAnswerDAO();
        QuizErrorDTO error = new QuizErrorDTO();

        try {
            if (answer.checkQuiz(quizID)) {
                QuizQuestionDAO dao = new QuizQuestionDAO();
                QuizQuestionDTO dto = new QuizQuestionDTO(questionNo, 0, 0, content, explanation, "", mediaLink, true);
                throw new NamingException("Missing method here");
//                boolean result = dao.editQuizQuestion(dto);
            } else {
                error.setFailToChangeError("Quiz have been done by someone, you can disable it only");
                request.setAttribute("UPDATE_QUESTION_ERROR", error);
            }
        } catch (NamingException ex) {
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
