package Servlet.SubjectRegistration;

import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.PackageDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "GetPackageForUpdatingServlet", urlPatterns = {"/GetPackageForUpdatingServlet"})
public class GetPackageForUpdatingServlet extends HttpServlet {

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
                    SubjectDTO currSub = (SubjectDTO) session.getAttribute("CURRENT_SUBJECT");
                    List<PackageDTO> packages = currSub.getPackages();
                    int packageID = Integer.parseInt(request.getParameter("txtPackageID"));
                    
                    PackageDTO currPackage = null;
                    for (PackageDTO pack : packages) {
                        if (pack.getPackageID() == packageID) {
                            currPackage = pack;
                        }
                    }
                    
                    if (currPackage != null) {
                        session.setAttribute("CURRENT_PACKAGE", currPackage);
                        log("CURRENT_PACKAGE: " + currPackage.getName());
                        url = RESULT_PAGE;
                    } else {
                        url = ERROR_PAGE;
                    }
                }
            }
        } catch (NumberFormatException ex) {
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
