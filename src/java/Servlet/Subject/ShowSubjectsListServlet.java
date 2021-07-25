/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Subject;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.PackageDAO;
import Temp.UsersDAO;
import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.PackageDTO;
import DTO.User.UserDTO;
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
@WebServlet(name = "ShowSubjectsListServlet", urlPatterns = {"/ShowSubjectsListServlet"})
public class ShowSubjectsListServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String ERROR_PAGE = "error.html";
    private final String RESULT_PAGE = "DashboardSubjectTable.jsp";

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
                if (currUser != null) {
                    String userID = currUser.getUserID();
                    String userRole = currUser.getRole();

                    SubjectDAO subDAO = new SubjectDAO();
                    List<SubjectDTO> subjectList = subDAO.getAvailableSubjectList(userID, userRole);                    

                    UsersDAO userDAO = new UsersDAO();
                    List<UserDTO> expertList = userDAO.getAllExperts();
//                    for (UserDTO exp : expertList) {
//                        log("Expert: " + exp.getFullName());
//                    }
                    log(subjectList.toString());
                    PackageDAO packageDAO = new PackageDAO();

                    List<SubjectDTO> enabledList = new ArrayList<>();
                    List<SubjectDTO> disabledList = new ArrayList<>();

                    for (SubjectDTO sub : subjectList) {
                        packageDAO.getPackagesBySubjectID(sub.getSubjectID());
                        sub.setPackages(packageDAO.getPackageList());

                        if (sub.isStatus()) {
                            enabledList.add(sub);
                        } else {
                            disabledList.add(sub);
                        }
                    }

                    List<PackageDTO> defaultPackages = new ArrayList<>();
                    defaultPackages.add(new PackageDTO(0, 0, "Default package 3 months", 3, true, 1200, 1200, "This is a default package with a 3-month duration."));
                    defaultPackages.add(new PackageDTO(0, 0, "Default package 6 months", 3, true, 2400, 2400, "This is a default package with a 6-month duration."));
                    defaultPackages.add(new PackageDTO(0, 0, "Default package unlimited duration", 0, true, 3600, 3600, "This is a default package with an unlimited duration."));

                    log("enabled list: " + enabledList.toString());
                    log("disenabled list: " + disabledList.toString());
                    request.setAttribute("DEFAULT_PACKAGES", defaultPackages);
                    request.setAttribute("EXPERT_LIST", expertList);
                    request.setAttribute("ENABLED_SUBJECTS_LIST", enabledList);
                    request.setAttribute("DISABLED_SUBJECTS_LIST", disabledList);
                    url = RESULT_PAGE;
                }
            }
        } catch (NamingException | SQLException ex) {
            url = ERROR_PAGE;
            log(ex.toString());
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
