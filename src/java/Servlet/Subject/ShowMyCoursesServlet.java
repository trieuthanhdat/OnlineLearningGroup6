/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Subject;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.RegistrationDTO;
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
@WebServlet(name = "ShowMyCoursesServlet", urlPatterns = {"/ShowMyCoursesServlet"})
public class ShowMyCoursesServlet extends HttpServlet {
    
    private final String ERROR_PAGE = "error.html";
    private final String MY_COURSES_PAGE = "MyCourse.jsp";
    
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
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO currUser = (UserDTO)session.getAttribute("CURRENT_USER");
                if (currUser != null) {
                    
                    RegistrationDAO regDAO = new RegistrationDAO();
                    List<RegistrationDTO> regList = regDAO.getUserRegistrations(currUser.getEmail());
                    List<Integer> subIDList = new ArrayList<>();
                    
                    for (RegistrationDTO regItem : regList) {
                        // find all registrations which status is valid
                        if (regItem.isStatus()) {
                            subIDList.add(regItem.getSubjectID());
                        }
                    }
                    
                    List<SubjectDTO> myCourses = new ArrayList<>();
                    SubjectDAO subDAO = new SubjectDAO();
                    
                    for (Integer subID : subIDList) {
                        SubjectDTO sub = subDAO.getSubjectByID(subID);
                        // find all enabled subjects and add to myCourse
                        if (sub.isStatus()) {
                            myCourses.add(sub);
                            log(sub.getTitle());
                        }
                    }

                    request.setAttribute("MY_COURSES", myCourses);
                    log(myCourses.toString());
                    url = MY_COURSES_PAGE;
                }
            }
        } catch (NamingException | SQLException ex) {
           log(ex.toString());
        } finally {
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
