package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DTO.Lesson.LessonDTO;
import DTO.Lesson.LessonDetailsDTO;
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
@WebServlet(name = "UpdateLessonDetailsServlet", urlPatterns = {"/UpdateLessonDetailsServlet"})
public class UpdateLessonDetailsServlet extends HttpServlet {

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

        String url = ERROR_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
                if (currUser != null) {
                    if (currUser.getRole().equals("Admin") || currUser.getRole().equals("Expert")) {
                        LessonDTO updateLesson = (LessonDTO) session.getAttribute("CURRENT_LESSON");
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
                            topicID = Integer.parseInt(request.getParameter("txtTopicID"));
                            quizID = Integer.parseInt(request.getParameter("txtQuizID"));
                        } else {
                            htmlContent = request.getParameter("txtHtmlContent");
                        }

                        session.setAttribute("UPDATE_LESSON_INFO", new LessonDTO(updateLesson.getLessonID(), updateLesson.getSubjectID(), lessonName, order, lessonType, topicID, true,
                                new LessonDetailsDTO(quizID, videoLink, htmlContent)));
                        LessonDAO dao = new LessonDAO();
                        LessonDTO currLesson = dao.getLessonByID(updateLesson.getLessonID());
                        int oldTopicID = currLesson.getTopicID();

                        boolean checkResult = dao.checkValidOrderForUpdating(updateLesson.getSubjectID(), order, oldTopicID, topicID);
                        log("Update Lesson Servlet: checkResult = " + checkResult);
                        if (checkResult) {
                            int oldOrder = currLesson.getOrder();

                            LessonDetailsDTO details = new LessonDetailsDTO(quizID, videoLink, htmlContent);
                            LessonDTO upLesson = new LessonDTO(updateLesson.getLessonID(), updateLesson.getSubjectID(), lessonName, order, lessonType, topicID, currLesson.isStatus(), details);
                            boolean result = dao.updateLesson(upLesson, oldOrder, oldTopicID);
                            log("Update Lesson Servlet: result = " + result);
                            if (result) {
                                url = RESULT_PAGE;
                                session.removeAttribute("CURRENT_LESSON");
                                session.removeAttribute("UPDATE_LESSON_INFO");

                                LessonDAO lessonDAO = new LessonDAO();
                                List<LessonDTO> currSubLessons = lessonDAO.getLessonsBySubjectID(upLesson.getSubjectID());
                                session.setAttribute("SUB_LESSONS", currSubLessons);
                            }
                        } else {
                            url = ERROR_PAGE;
                        }
                    }
                }
            }
        } catch (NamingException | SQLException | NumberFormatException ex) {
            log(ex.toString());
        } finally {
            response.sendRedirect(url);
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
