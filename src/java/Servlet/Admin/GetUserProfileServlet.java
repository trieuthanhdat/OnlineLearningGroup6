package Servlet.Admin;

import DTO.User.UserDTO;
import DTO.User.UserProfileDTO;
import Temp.UserProfileDAO;
import Temp.UsersDAO;
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
@WebServlet(name = "GetUserProfileServlet", urlPatterns = {"/GetUserProfileServlet"})
public class GetUserProfileServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String ERROR_PAGE = "UserTable";
    private final String RESULT_PAGE = "UserTable";
    
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
                if (currUser != null && currUser.getRole().equals("Admin")) {
                    String userID = request.getParameter("txtUserID");
                    UsersDAO dao = new UsersDAO();
                    UserDTO profileUser = dao.getCurrUserByID(userID);                    
                    if (profileUser != null) {
                        log("SEE_USER: " + profileUser.getFullName() + " - " + profileUser.getUserID());
                        
                        UserProfileDAO profileDAO = new UserProfileDAO();                        
                        UserProfileDTO currProfile = profileDAO.getUserProfileByEmail(profileUser.getEmail());
                        
                        log("SEE_PROFILE: " + currProfile.getGender() + " - " + currProfile.getPhone() + " - " + currProfile.getAddress());
                        
                        session.setAttribute("SEE_USER", profileUser);
                        session.setAttribute("SEE_PROFILE", currProfile);
                        
                        url = RESULT_PAGE;
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
