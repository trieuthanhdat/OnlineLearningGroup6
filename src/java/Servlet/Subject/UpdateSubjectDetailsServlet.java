package Servlet.Subject;

import DAO.Lesson.LessonDAO;
import DAO.Subject.SubjectDAO;
import DTO.Subject.SubjectDTO;
import DTO.Subject.SubjectDetailsDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateSubjectDetailsServlet", urlPatterns = {"/UpdateSubjectDetailsServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
   maxFileSize = 1024 * 1024 * 5,
   maxRequestSize = 1024 * 1024 * 5 * 5)
public class UpdateSubjectDetailsServlet extends HttpServlet {

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
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
                if (currUser != null) {
                    SubjectDTO currSub = (SubjectDTO) session.getAttribute("CURRENT_SUBJECT");
                    log("CURRENT_SUBJECT_ID: " + currSub.getSubjectID());
                    
                    int subID = currSub.getSubjectID();
                    String title = request.getParameter("txtSubjectTitle");
                    
                    int categoryID = Integer.parseInt(request.getParameter("txtCategoryID"));
                    String thumbnail = request.getParameter("txtThumbnail");
                    if (thumbnail.isEmpty()) {
                        thumbnail = currSub.getThumbnail();
                    }
                    log("thumbnail: " + thumbnail);
                    
                    String tagLine = "";
                    String briefInfo = request.getParameter("txtBriefInfo");
                    String description = request.getParameter("txtDescription");
                    
                    boolean featureFlag = true;
                    log("txtFeatureFlag: " + request.getParameter("txtFeatureFlag"));
                    if (request.getParameter("txtFeatureFlag") == null) {
                        featureFlag = false;
                    }
                    log("FEATURE_FLAG: " + featureFlag);
                    
                    boolean status = Boolean.parseBoolean(request.getParameter("txtStatus"));                    

                    SubjectDetailsDTO subDetails = new SubjectDetailsDTO(tagLine, description);
                    SubjectDTO sub = new SubjectDTO(subID, categoryID, title, 0, thumbnail, "", briefInfo, subDetails, null, status, featureFlag);

                    SubjectDAO dao = new SubjectDAO();
                    if (dao.updateSubject(sub)) {
                        LessonDAO lessonDAO = new LessonDAO();
                        
                        if (status != currSub.isStatus() && lessonDAO.checkSubjectHasValidLessons(subID)) {
                            dao.updateSubjectStatus(subID);
                        } else {
                            log("Not valid for update status.");
                        }
                        
                        session.removeAttribute("CURRENT_SUBJECT");
                        log("UPDATED_SUBJECT: " + sub.getTitle());
                        url = RESULT_PAGE;
                    } else {
                        url = ERROR_PAGE;
                    }
                }
            }
        } catch (NamingException | SQLException | NumberFormatException ex) {
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
