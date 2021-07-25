/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Subject;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.PackageDAO;
import DTO.Subject.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ShowSearchCoursesServlet", urlPatterns = {"/ShowSearchCoursesServlet"})
public class ShowSearchCoursesServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";    
    private final String SEARCH_COURSES_PAGE = "SearchedCourses.jsp";    
    
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
            String searchCourseName = request.getParameter("txtCourseName");  
            String categoryIDasString = request.getParameter("txtCategoryID");
            
            int categoryID = 0;
            if(categoryIDasString !=null){
                categoryID = Integer.parseInt(request.getParameter("txtCategoryID"));
            }
            
            if (searchCourseName == null) 
                searchCourseName = "";
            else searchCourseName = searchCourseName.trim().toLowerCase();
            
            SubjectDAO subDAO = new SubjectDAO();
            List<SubjectDTO> searchList = subDAO.searchCourse(searchCourseName,categoryID);            
            
            PackageDAO packageDAO = new PackageDAO();
            for (SubjectDTO sub : searchList) {
                packageDAO.getPackagesBySubjectID(sub.getSubjectID());
                sub.setPackages(packageDAO.getPackageList());
                log(sub.getTitle());
            }
            request.setAttribute("SEARCH_COURSES_LIST", searchList);
            url = SEARCH_COURSES_PAGE;
            
            
        } catch (NamingException | SQLException ex) {
           ex.printStackTrace();
        } finally {
            if (url.equals(WELCOME_PAGE)) {
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
