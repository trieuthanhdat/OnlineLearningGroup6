package Servlet.Subject;

import DAO.Lesson.LessonDAO;
import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.PackageDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Lesson.LessonDTO;
import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.RegistrationDTO;
import DTO.User.UserDTO;
import Temp.UsersDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ShowCourseDetailsServlet", urlPatterns = {"/ShowCourseDetailsServlet"})
public class ShowCourseDetailsServlet extends HttpServlet {

    private final String ERROR_PAGE = "ErrorPage";
    private final String WELCOME_PAGE = "WelcomePage";
    private final String COURSE_DETAILS_PAGE = "CourseDetail.jsp";

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
            HttpSession session = request.getSession(true);
            UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
            int subID = Integer.parseInt(request.getParameter("txtSubjectID"));
            SubjectDAO subDAO = new SubjectDAO();
            UsersDAO userDAO = new UsersDAO();
            SubjectDTO currSub = subDAO.getSubjectByID(subID);
            if (currSub != null) {
                currSub.setDetails(subDAO.getSubjectDetailsByID(subID));

                PackageDAO packageDAO = new PackageDAO();
                packageDAO.getPackagesBySubjectID(subID);
                currSub.setPackages(packageDAO.getPackageList());

                LessonDAO lessonDAO = new LessonDAO();

                List<LessonDTO> lessonList = lessonDAO.getLessonsBySubjectID(subID);
                List<LessonDTO> topicList = new ArrayList<>();
                List<LessonDTO> subLessonList = new ArrayList<>();
                for (LessonDTO lessonDTO : lessonList) {
                    if (lessonDTO.getTopicID() == 0) {

                        topicList.add(lessonDTO);
                        log("topicID: " + String.valueOf(lessonDTO.getLessonID()));
                    } else {
                        subLessonList.add(lessonDTO);
                        log("lessonID: " + String.valueOf(lessonDTO.getLessonID()));
                    }

                }

                RegistrationDAO resDAO = new RegistrationDAO();
                request.setAttribute("TOPIC_LIST", topicList);
                request.setAttribute("SUBLESSON_LIST", subLessonList);
                request.setAttribute("CURRENT_SUBJECT", currSub);
                request.setAttribute("CURRENT_SUBJECT_DETAIL", subDAO.getSubjectDetailsByID(subID));
                request.setAttribute("REGIST_NUMBER", resDAO.getNumberRegisSubject(subID));
                request.setAttribute("AUTHOR_NAME", userDAO.getUserFullName(currSub.getOwnerID()));
                List<SubjectDTO> list = subDAO.getSubjectByCategory(currSub.getSubjectCategoryID());
                request.setAttribute("MORE_SUBJECT", list);
                if (currUser != null) {
                    List<RegistrationDTO> myRegs = resDAO.getUserRegistrations(currUser.getEmail());
                    RegistrationDTO currReg = null;
                    for (RegistrationDTO reg : myRegs) {
                        if (reg.isStatus() && reg.getSubjectID() == subID) {
                            currReg = reg;
                            break;
                        }
                    }
                    
                    request.setAttribute("OWNCOURSE", currReg);
                } else {
                    request.setAttribute("OWNCOURSE", null);
                }
                url = COURSE_DETAILS_PAGE;
            } else {
                url = ERROR_PAGE;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (url.equals(WELCOME_PAGE) || url.equals(ERROR_PAGE)) {
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
