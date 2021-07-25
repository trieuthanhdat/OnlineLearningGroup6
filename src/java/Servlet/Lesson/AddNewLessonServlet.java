package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DAO.Quiz.QuizDAO;
import DTO.Lesson.LessonDTO;
import DTO.Lesson.LessonDetailsDTO;
import DTO.Subject.SubjectDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddNewLessonServlet", urlPatterns = {"/AddNewLessonServlet"})
public class AddNewLessonServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String ERROR_PAGE = "SubjectTable";
    private final String RESULT_PAGE = "SubjectTable";
    
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
        
        String url = WELCOME_PAGE;
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO currUser = (UserDTO)session.getAttribute("CURRENT_USER");
                if (currUser != null) {
                    if (currUser.getRole().equals("Admin") || currUser.getRole().equals("Expert")) {
                        SubjectDTO currSub = (SubjectDTO) session.getAttribute("CURRENT_SUBJECT");
                        int currSubID = currSub.getSubjectID();
                        String lessonName = request.getParameter("txtLessonName");
                        String lessonType = request.getParameter("txtLessonType");
                        int order = Integer.parseInt(request.getParameter("txtOrder"));
                        int topicID = 0;
                        String videoLink = "";
                        String htmlContent = "";
                        int quizID = 0;
                        
                        if (lessonType.equals("Lesson")) {
                            topicID = Integer.parseInt(request.getParameter("txtTopicID"));
                            videoLink = request.getParameter("txtVideoLink");
                            if (videoLink == null) {
                                videoLink = "";
                            }
                            htmlContent = request.getParameter("txtHtmlContent");
                        } else if (lessonType.equals("Quiz")) {
                            quizID = Integer.parseInt("txtQuizID");                            
                        }
                        
                        session.setAttribute("NEW_LESSON_INFO", new LessonDTO(0, currSub.getSubjectID(), lessonName, order, lessonType, topicID, true, 
                                                    new LessonDetailsDTO(quizID, videoLink, htmlContent)));
                        log("SubjectID: " + currSubID + " - Order: " + order + " - TopicID: " + topicID);                        
                        LessonDAO dao = new LessonDAO();                        
                        boolean result = dao.checkValidOrderForAdding(currSubID, order, topicID);
                        log("Check valid order result: " + result);
                        if (result) {
                            LessonDetailsDTO details = new LessonDetailsDTO(quizID, videoLink, htmlContent);
                            LessonDTO newLesson = new LessonDTO(0, currSubID, lessonName, order, lessonType, topicID, true, details);
                            boolean addResult = dao.addNewLesson(newLesson);
                            log("Add new lesson result: " + addResult);
                            if (addResult) {
                                session.removeAttribute("NEW_LESSON_INFO");
                                session.removeAttribute("CURRENT_LESSON");
                                
                                LessonDAO lessonDAO = new LessonDAO();
                                List<LessonDTO> currSubLessons = lessonDAO.getLessonsBySubjectID(currSub.getSubjectID());
                                session.setAttribute("SUB_LESSONS", currSubLessons);
                                
                                QuizDAO quizDAO = new QuizDAO();
                                session.setAttribute("QUIZZES_LIST", quizDAO.getQuizList(currSub.getSubjectID()));
                                log("QUIZ_LIST_SIZE: " + quizDAO.getQuizList(currSub.getSubjectID()));

                                session.setAttribute("TOPICS_LIST", lessonDAO.getTopicsBySubjectID(currSub.getSubjectID()));
                                log("TOPICS_LIST_SIZE: " + lessonDAO.getTopicsBySubjectID(currSub.getSubjectID()).size());
                                
                                url = RESULT_PAGE;
                            }
                        }
                    }
                }
            }
        } catch (NamingException | SQLException | ClassNotFoundException | NumberFormatException ex) {
            log(ex.toString());
            ex.printStackTrace();
        } 
        response.sendRedirect(url);
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
