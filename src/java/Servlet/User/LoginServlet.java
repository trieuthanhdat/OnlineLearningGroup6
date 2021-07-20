package Servlet.User;

import Error.LoginError;
import Temp.UsersDAO;
import DTO.User.UserDTO;
import DTO.User.UserProfileDTO;
import Temp.UserProfileDAO;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGGED_IN_PAGE = "HomePage";
    private final String INVALID_PAGE = "WelcomePage";

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

        String url = INVALID_PAGE;       
        
        try {                        
            LoginError errors = new LoginError();            
            boolean foundErr = false;
                        
            String email = request.getParameter("txtEmail");                
            String password = request.getParameter("txtPassword");                        
            
            errors.setLoginEmail(email);
            errors.setLoginPassword(password);
            
            if (!email.trim().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                foundErr = true;
                errors.setWrongEmailFormatError("Sorry, we don't recognize that username or password. Please try again!");
                
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_ERROR", errors);                
            }
            
            if (!foundErr) {
                log("LoginServlet: " + email + " - " + password);            
                UsersDAO dao = new UsersDAO();
                UserProfileDAO profileDao = new UserProfileDAO();
                UserDTO result = dao.checkLogin(email, password);            
                if (result != null) {
                    UserProfileDTO profile = profileDao.getUserProfileByEmail(result.getEmail());
                    log(profile.getAvartar());
                    url = LOGGED_IN_PAGE;                
                    HttpSession session = request.getSession();
                    session.removeAttribute("LOGIN_ERROR");     
                    session.setAttribute("CURRENT_USER", result);
                    session.setAttribute("CURRENT_PROFILE", profile);
                    log(result.getPassword());
                } else {
                    errors.setWrongPasswordError("Sorry, we don't recognize that username or password. Please try again!");                                    
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_ERROR", errors);
                }    
            }
            
            log(url);
        } catch (NamingException ex) {
            log("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException: " + ex.getMessage());
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
