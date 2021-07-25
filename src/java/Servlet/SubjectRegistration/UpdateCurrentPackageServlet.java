package Servlet.SubjectRegistration;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.PackageDAO;
import DTO.Subject.SubjectDTO;
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
@WebServlet(name = "UpdateCurrentPackageServlet", urlPatterns = {"/UpdateCurrentPackageServlet"})
public class UpdateCurrentPackageServlet extends HttpServlet {

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
                    int packageID = Integer.parseInt(request.getParameter("txtPackageID"));
                    String name = request.getParameter("txtPackageName");
                    int accessDuration = Integer.parseInt(request.getParameter("txtAccessDuration"));
                    float listPrice = Float.parseFloat(request.getParameter("txtListPrice"));
                    float salePrice = Float.parseFloat(request.getParameter("txtSalePrice"));
                    String description = request.getParameter("txtDescription");
                    boolean status = Boolean.parseBoolean(request.getParameter("txtStatus"));
                    
                    PackageDAO dao = new PackageDAO();
                    boolean result = dao.updatePackage(new PackageDTO(packageID, 0, name, accessDuration, status, listPrice, salePrice, description));
                    if (result) {                        
                        SubjectDTO currSub = (SubjectDTO) session.getAttribute("CURRENT_SUBJECT");
                        SubjectDAO subDAO = new SubjectDAO();
                        currSub = subDAO.getSubjectByID(currSub.getSubjectID());                        
                        currSub.setDetails(subDAO.getSubjectDetailsByID(currSub.getSubjectID()));                        
                        dao.getPackagesBySubjectID(currSub.getSubjectID());                        
                        currSub.setPackages(dao.getPackageList());
                        log("CURRENT_SUBJECT_PACKAGES: " + currSub.getPackages().get(0).getName());
                        
                        session.setAttribute("CURRENT_SUBJECT", currSub);
                        session.removeAttribute("CURRENT_PACKAGE");
                        url = RESULT_PAGE;
                    } else {
                        url = ERROR_PAGE;
                    }
                }
            }
        } catch (NamingException | NumberFormatException | SQLException ex) {
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
