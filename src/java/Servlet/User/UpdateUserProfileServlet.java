/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.User;

import DTO.User.UserDTO;
import Temp.UserProfileDAO;
import Temp.UsersDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateUserProfileServlet", urlPatterns = {"/UpdateUserProfileServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UpdateUserProfileServlet extends HttpServlet {

    private final String UPDATE_USER_PROFILE = "ProfilePage.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String WELCOME_PAGE = "WelcomePage.jsp";

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
        String url = ERROR_PAGE;
        String name = request.getParameter("txtName");
        String gender = request.getParameter("Gender");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        String avatar = request.getParameter("picture");
        String oldPass = request.getParameter("txtOldPassword");
        String newPass = request.getParameter("txtNewPassword");
        String confirmPass = request.getParameter("txtConfirmPassword");
        //update name
        //update gender phone address
//        log("\nName: " + name);
//        log("Gender: " + gender);
//        log("Phone: " + phone);
//        log("Address: " + address);
//        log("Avatar: " + avatar);
//        log("OldPass: " + oldPass);
//        log("New Pass: " + newPass);
//        log("Confirm: " + confirmPass);
        try {
            HttpSession session = request.getSession(false);
            UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
            if (currUser != null) {
                String email = currUser.getEmail();
                UserProfileDAO updao = new UserProfileDAO();
                UsersDAO udao = new UsersDAO();
                if (avatar != null) {
                    //update avatar
                    String values[] = avatar.split("/");
                    avatar = values[values.length-1];
//                    log("Avatar: " + avatar);
                    boolean resultEmail = updao.updateUserAvatar(email, avatar);
                    if (resultEmail) {
                        url = UPDATE_USER_PROFILE;
                    }
                }
                if (name != null) {
                    boolean resultName = udao.updateUserName(email, name);
                    log(resultName + " ");
                    if (resultName) {
                        session.setAttribute("fullname", name);
                        url = UPDATE_USER_PROFILE;
                    }
                }
                if (gender != null) {
                    //phone va address dc null
                    boolean resultProfile = updao.updateUserProfile(email, gender, phone, address);
                    if (resultProfile) {
                        url = UPDATE_USER_PROFILE;
                    }
                }
                if (oldPass != null && newPass != null && confirmPass != null) {
                    //check if old pass is exist
                    boolean exist = udao.checkExistPasswordByEmail(email, oldPass);
                    if (exist && newPass.equals(confirmPass)) {
                        boolean result = udao.updatePassword(newPass, email);
                        if (result) {
                            url = UPDATE_USER_PROFILE;
                        }
                    }
                }
                session.setAttribute("CURRENT_USER", udao.getCurrUserByID(currUser.getUserID()));
                session.setAttribute("CURRENT_PROFILE", updao.getUserProfileByEmail(email));
            } else {
                url = WELCOME_PAGE;
            }
        } catch (SQLException ex) {
            log("Check UpdateUserProfileServlet SQL Exception - " + ex);
        } catch (NamingException ex) {
            log("Check UpdateUserProfileServlet NamingException - " + ex);
        } finally {
            log(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
