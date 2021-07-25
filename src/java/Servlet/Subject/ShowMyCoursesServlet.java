/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Subject;

import DAO.Lesson.LessonDAO;
import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.RegistrationDTO;
import DTO.TrackingProgress.TrackingProgressDTO;
import DTO.User.UserDTO;
import Temp.TrackingProgressDAO;
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
import utils.Progress;

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
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");

                if (currUser != null) {

                    RegistrationDAO regDAO = new RegistrationDAO();

                    List<RegistrationDTO> regList = regDAO.getUserRegistrations(currUser.getEmail());
                    List<RegistrationDTO> availableRegList = new ArrayList<>();
                    // find all registrations which status is valid
                    for (RegistrationDTO regItem : regList) {
                        if (regItem.isStatus()) {
                            availableRegList.add(regItem);
                        }
                    }

                    SubjectDAO subDAO = new SubjectDAO();
                    List<SubjectDTO> myCourses = new ArrayList<>();

                    TrackingProgressDAO trackingdao = new TrackingProgressDAO();
                    List<TrackingProgressDTO> trackList = new ArrayList<>();

                    LessonDAO lessondao = new LessonDAO();
                    List<Progress> progressList = new ArrayList<>();

                    // get all tracking progress as well as available courses from there
                    for (RegistrationDTO reg : availableRegList) {
                        myCourses.add(subDAO.getSubjectByID(reg.getSubjectID()));
                        log("current subjectID: " + reg.getSubjectID());
                        int numOfFinishQuiz = trackingdao.numberOfFinishLessonQuiz(reg.getRegistrationID());
                        log("number of completed quiz: " + numOfFinishQuiz);
                        int numOfQuiz = lessondao.getNumOfQuizzesFromSubject(reg.getSubjectID());
                        log("Number of quiz in subject: " + numOfQuiz);
                        progressList.add(new Progress(numOfQuiz, numOfFinishQuiz, reg.getSubjectID()));
                    }                    
                    request.setAttribute("PROGRESS_LIST", progressList);
                    //----------------------------------------------------
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
