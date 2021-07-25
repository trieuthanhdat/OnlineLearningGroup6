package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DAO.Quiz.QuizDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Lesson.LessonDTO;
import DTO.Quiz.QuizDTO;
import DTO.TrackingProgress.TrackingProgressDTO;
import DTO.User.UserDTO;
import Temp.TrackingProgressDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ShowCurrentLessonServlet", urlPatterns = {"/ShowCurrentLessonServlet"})
public class ShowCurrentLessonServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String ERROR_PAGE = "ErrorPage";
    private final String SHOW_LEARNING_PAGE = "Lectures.jsp";

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
                if (currUser != null && currUser.getRole().equals("User")) {
                    int lessonID = 0;
                    String lessonIDasString = request.getParameter("txtLessonID");
                    if (lessonIDasString != null) {
                        lessonID = Integer.parseInt(lessonIDasString);
                    }
                    log("LessonID: " + lessonID);

                    List<LessonDTO> lessons = (List<LessonDTO>) session.getAttribute("LESSONS_LIST");
                    boolean validLesson = false;
                    for (LessonDTO ls : lessons) {
                        if (lessonID == 0) {
                            // get first lesson
                            validLesson = true;
                            lessonID = ls.getLessonID();
                            break;
                        } else if (ls.getLessonID() == lessonID) {
                            validLesson = true;
                            break;
                        }
                    }

                    log(validLesson + " " + lessonID);
                    if (validLesson) {

                        LessonDAO lessonDAO = new LessonDAO();
                        LessonDTO currLesson = lessonDAO.getCurrLessonDetails(lessonID);                        
                        if (currLesson != null) {
                            log("Current lesson: " + currLesson.getName());    
                            //check if current lesson is a quiz
                            if (currLesson.getDetails().getQuizID() != 0) {
                                
                                QuizDAO quizDAO = new QuizDAO();
                                QuizDTO currQuiz = quizDAO.getQuiz(currLesson.getDetails().getQuizID());                                                                
                                                                
                                log(currQuiz.getName());
                                session.setAttribute("CURRENT_QUIZ", currQuiz);
                            } else {
                                session.removeAttribute("CURRENT_QUIZ");
                            }
                            
                            TrackingProgressDAO trackDAO = new TrackingProgressDAO();
                            RegistrationDAO regDAO = new RegistrationDAO();
                            int regID = regDAO.getRegistrationIDBySubIDandEmail(currLesson.getSubjectID(), currUser.getEmail());
                            List<TrackingProgressDTO> trackList = trackDAO.getTrackingProgressByRegistrationID(regID);
                            // User haven't done a single quiz
                            List<LessonDTO> currLessons = (List<LessonDTO>) session.getAttribute("LESSONS_LIST");
                            List<LessonDTO> currQuizzes = new ArrayList<>();
                            for (LessonDTO l : currLessons) {
                                if (l.getType().equals("Quiz")) {
                                    currQuizzes.add(l);
                                }
                            }

                            List<TrackingProgressDTO> orderedTrackList = new ArrayList<>();
                            for (LessonDTO quiz : currQuizzes) {
                                for (TrackingProgressDTO track : trackList) {
                                    if (track.getLessonid() == quiz.getLessonID()) {
                                        orderedTrackList.add(track);
                                        log("Current tracking progress: " + track.getRegistrationid() + " - " + track.getLessonid());
                                    }
                                }
                            }
                            
                            List<LessonDTO> enabledQuizzes = new ArrayList<>();

                            if (trackList.size() == 1) {
                                // only the first quiz is available
                                enabledQuizzes.add(currQuizzes.get(0));                                
                            } else {
                                for (TrackingProgressDTO track : orderedTrackList) {
                                    if (track.isStatus()) {  
                                        for (LessonDTO quiz : currQuizzes) {
                                            if (quiz.getLessonID() == track.getLessonid()) {
                                                enabledQuizzes.add(quiz);
                                                break;
                                            }
                                        }                                        
                                    } else {
                                        for (LessonDTO quiz : currQuizzes) {
                                            if (quiz.getLessonID() == track.getLessonid()) {
                                                enabledQuizzes.add(quiz);
                                                break;
                                            }
                                        }
                                        // last available quiz
                                        break;
                                    }
                                }                                
                            }
                            
                            session.setAttribute("ENABLED_QUIZZES", enabledQuizzes);
                            session.setAttribute("CURRENT_LESSON", currLesson);
                            url = SHOW_LEARNING_PAGE;
                        }
                    }
                }
            }                        
        } catch (NamingException | SQLException | ClassNotFoundException ex) {
            log(ex.getMessage());
        } finally {
            if (url.equals(ERROR_PAGE)) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
