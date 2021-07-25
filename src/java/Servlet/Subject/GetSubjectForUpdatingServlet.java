package Servlet.Subject;

import DAO.Lesson.LessonDAO;
import DAO.Quiz.QuizDAO;
import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.PackageDAO;
import DTO.Lesson.LessonDTO;
import DTO.Quiz.QuizDTO;
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
@WebServlet(name = "GetSubjectForUpdatingServlet", urlPatterns = {"/GetSubjectForUpdatingServlet"})
public class GetSubjectForUpdatingServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String url = WELCOME_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
                if (currUser != null && (currUser.getRole().equals("Admin") || currUser.getRole().equals("Expert"))) {
                    int subID = Integer.parseInt(request.getParameter("txtSubjectID"));
                    SubjectDAO dao = new SubjectDAO();
                    SubjectDTO currSub = dao.getSubjectByID(subID);
                    if (currSub != null) {
                        currSub.setDetails(dao.getSubjectDetailsByID(subID));
                        log("DESCRIPTION: " + currSub.getDetails().getDescription());

                        PackageDAO packageDAO = new PackageDAO();
                        packageDAO.getPackagesBySubjectID(subID);
                        currSub.setPackages(packageDAO.getPackageList());

                        LessonDAO lessonDAO = new LessonDAO();
                        List<LessonDTO> currSubLessons = lessonDAO.getLessonsBySubjectID(subID);
                        List<LessonDTO> selectLesson = lessonDAO.getLearningLessonsFromSubject(subID);

                        QuizDAO quizDAO = new QuizDAO();
                        session.setAttribute("QUIZZES_LIST", quizDAO.getQuizList(currSub.getSubjectID()));
                        log("QUIZ_LIST_SIZE: " + quizDAO.getQuizList(currSub.getSubjectID()).size());
                        
                        session.setAttribute("TOPICS_LIST", lessonDAO.getTopicsBySubjectID(currSub.getSubjectID()));
                        log("TOPICS_LIST_SIZE: " + lessonDAO.getTopicsBySubjectID(currSub.getSubjectID()).size());
                        
                        session.removeAttribute("CURRENT_PACKAGE");
                        session.removeAttribute("CURRENT_LESSON");
                        session.setAttribute("CURRENT_SUBJECT", currSub);
                        session.setAttribute("SUB_LESSONS", currSubLessons);
                        session.setAttribute("LEARN_LESSON", selectLesson);
                        log("Current Subject: " + currSub.getTitle());
                        QuizDAO quizdao = new QuizDAO();
                        List<QuizDTO> quizlist = quizdao.getQuizList(currSub.getSubjectID());
                        log("list 1quiz" + quizlist.get(0).toString());;
                        if (quizlist != null) {
                            session.setAttribute("QUIZ_LIST", quizlist);
                        }
                        url = RESULT_PAGE;
                    } else {
                        url = ERROR_PAGE;
                    }
                }
            }
        } catch (NumberFormatException | NamingException | ClassNotFoundException | SQLException ex) {
            url = ERROR_PAGE;
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
