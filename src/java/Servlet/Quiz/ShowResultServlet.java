/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Quiz;

import DTO.Answer.AnswerDTO;
import DTO.Quiz.QuizOptionDTO;
import DTO.Question.QuizQuestionDTO;
import DAO.Answer.UserAnswerDAO;
import DTO.Answer.UserAnswerDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShowResultServlet", urlPatterns = {"/ShowResultServlet"})
public class ShowResultServlet extends HttpServlet {

    private final static String QUIZ_INFO_PAGE = "TrackingProgress";

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

        String url = QUIZ_INFO_PAGE;

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("CURRENT_USER");
        List<QuizQuestionDTO> question = (List<QuizQuestionDTO>) session.getAttribute("QUIZ_QUESTION");
        UserAnswerDTO previous = (UserAnswerDTO) session.getAttribute("USER_SCORE");
        List<QuizOptionDTO> option = (List<QuizOptionDTO>) session.getAttribute("QUIZ_QUESTION_OPTION");
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();

        try {
            double point = 10 / question.size();// cal point for each question
            UserAnswerDAO dao = new UserAnswerDAO();
            List<AnswerDTO> Answer = new ArrayList();

            while (it.hasNext()) {
                String pattern = "^[0-9]*$";
                Map.Entry<String, String[]> answer = (Map.Entry<String, String[]>) it.next();
                String tmpKey = answer.getKey();
                if (tmpKey.matches(pattern)) {
                    int key = Integer.parseInt(tmpKey);
                    String[] value = answer.getValue();
                    AnswerDTO tmp = dao.copyAnswer(option, key, value[0], point);
                    Answer.add(tmp);
                }
            }

            double score = dao.compareAnswer(Answer);

            UserAnswerDTO userAnswer = new UserAnswerDTO(user.getUserID(), question.get(0).getQuizID(), (ArrayList<AnswerDTO>) Answer, score);
            String answerString = dao.createString(Answer);// compress string to create user answer
            
            if (previous == null) {// check xem user đã làm quiz hay chưa
                log("previous null");
                boolean result = dao.saveToServer(userAnswer, answerString);// push it up to db

                if (result) {
                    session.setAttribute("USER_SCORE", userAnswer);
                    log("DIEM NGUOI DUNG: "+userAnswer.getScore());
                }
            } else {// Đã làm rồi
                log("previous: " + previous.getQuizID() + " - "+ previous.getUserID());
                if (previous.getScore() <= score) {// điểm làm lại cao hơn điểm trc
                    boolean result = dao.updateNewestAnswer(userAnswer, answerString, user.getUserID());
                    
                    if (result) {
                        session.setAttribute("USER_SCORE", userAnswer);
                    }
                } else {
                    session.setAttribute("USER_SCORE", previous);
                }
                
            }

        } catch (NamingException ex) {
            Logger.getLogger(ShowResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowResultServlet.class.getName()).log(Level.SEVERE, null, ex);
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
