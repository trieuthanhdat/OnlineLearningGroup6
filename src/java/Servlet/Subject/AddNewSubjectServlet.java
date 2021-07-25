package Servlet.Subject;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.PackageDAO;
import DTO.Subject.SubjectDTO;
import DTO.Subject.SubjectDetailsDTO;
import DTO.SubjectRegistration.PackageDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "AddNewSubjectServlet", urlPatterns = {"/AddNewSubjectServlet"})
public class AddNewSubjectServlet extends HttpServlet {
    
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
                UserDTO currUser = (UserDTO)session.getAttribute("CURRENT_USER");
                if (currUser != null & currUser.getRole().toLowerCase().equals("admin")) {
                    int subjectID = 0;
                    String title = request.getParameter("txtSubjectTitle");
                    int categoryID = Integer.parseInt(request.getParameter("txtCategoryID"));
                    int numOfLessons = 0;
                    String ownerID = request.getParameter("txtOwnerID");
                    String thumbnailLink = request.getParameter("txtThumbnail");
                    String values[] = thumbnailLink.split("/");
                    thumbnailLink = values[values.length-1];
                    
                    String tagLine = "None";
                    String briefInfo = request.getParameter("txtBriefInfo");
                    String description = request.getParameter("txtDescription");
                    boolean status = false;
                    
                    session.setAttribute("NEW_SUBJECT", new SubjectDTO(0, categoryID, title, numOfLessons, thumbnailLink, ownerID, briefInfo, new SubjectDetailsDTO(tagLine, description), null, status, false));
                    
                    SubjectDetailsDTO newDetails = new SubjectDetailsDTO(tagLine, description);
                    SubjectDTO newSubject = new SubjectDTO(subjectID, categoryID, title, numOfLessons, thumbnailLink, ownerID, briefInfo, newDetails, null, status, false);
                    SubjectDAO dao = new SubjectDAO();
                    
                    boolean result1 = dao.addNewSubject(newSubject);
                    boolean result2 = dao.addNewSubjectDetails(newSubject); 
                    log("Add Result: " + result1 + " - " + result2);
                    if (result1 && result2) {                        
                        int latestSubID = dao.getLatestSubID();
                        PackageDAO packageDAO = new PackageDAO();
                        packageDAO.addNewPackage(new PackageDTO(0, latestSubID, "Default package 3 months", 3, true, 120000, 120000, "This is a default package with a 3-month duration."));
                        packageDAO.addNewPackage(new PackageDTO(0, latestSubID, "Default package 6 months", 6, true, 240000, 240000, "This is a default package with a 6-month duration."));
                        packageDAO.addNewPackage(new PackageDTO(0, latestSubID, "Default package 12 months", 12, true, 480000, 480000, "This is a default package with a 12-month duration."));
                        
                        session.removeAttribute("NEW_SUBJECT");
                        url = RESULT_PAGE;
                        log("AddNewSubjectServlet: Create new subject " + newSubject.getTitle() + " successfully!");
                        response.sendRedirect(url);
                    } else {
                        url = ERROR_PAGE;
                    }
                }
            }
        } catch (NumberFormatException | NamingException | SQLException ex) {
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
