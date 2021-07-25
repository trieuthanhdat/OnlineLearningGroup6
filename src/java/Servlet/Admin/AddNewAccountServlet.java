package Servlet.Admin;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Temp.UsersDAO;
import Temp.UserProfileDAO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import utils.Mail;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddNewAccountServlet", urlPatterns = {"/AddNewAccountServlet"})
public class AddNewAccountServlet extends HttpServlet {

    private final String HOME_PAGE = "UserTable";

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
        String url = HOME_PAGE;
        String useremail = request.getParameter("txtEmail");
        String fullname = request.getParameter("txtFullname");
        String role = request.getParameter("txtRole");
        String avatar = "";
        String gender = request.getParameter("Gender");
        String mobile = request.getParameter("txtMobile");
        String address = request.getParameter("txtAddress");        

        try {
            UsersDAO userdao = new UsersDAO();
            if (fullname.trim().length() == 0) {
                fullname = useremail;
            }                        

            boolean existEmail = userdao.checkExistUserEmail(useremail);
            if (existEmail) {
                String error = "Email has already existed in database !!!!";
                HttpSession session = request.getSession();
                session.setAttribute("EMAIL_EXIST_ERROR", error);
            } else {            
                //cap nhat ben user trc roi moi toi user profile
                String userid = userdao.getNextUserID();
                boolean status = true;//status mac dinh la true
                //lay ngay hien tai
                long millis = System.currentTimeMillis();
                java.sql.Date CreateDate = new java.sql.Date(millis);
                //cap nhat users
                String password = userdao.randomPass();
                boolean result1 = userdao.createNewUser(useremail, userid, role, fullname, password, status, CreateDate);
                //cap nhat user profile
                UserProfileDAO dao = new UserProfileDAO();
                boolean result2 = dao.createNewUserProfile(useremail, avatar, gender, mobile, address);
                if (result1 && result2) {
                    Mail.SendEmailToNewUser(useremail, password);
                }            
                log("RANDOM PASS: " + password);
            }
        } catch (SQLException | MessagingException | NamingException ex) {
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
