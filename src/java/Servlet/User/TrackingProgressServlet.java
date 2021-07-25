/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.User;

import DAO.Lesson.LessonDAO;
import DAO.Lesson.LessonDetailsDAO;
import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.MyRegistrationDAO;
import DAO.SubjectRegistration.PackageDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Answer.UserAnswerDTO;
import DTO.Lesson.LessonDTO;
import DTO.Quiz.QuizDTO;
import DTO.TrackingProgress.TrackingProgressDTO;
import DTO.User.UserDTO;
import Temp.TrackingProgressDAO;
import Temp.UsersDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "TrackingProgressServlet", urlPatterns = {"/TrackingProgressServlet"})
public class TrackingProgressServlet extends HttpServlet {

    private final static String QUIZ_INFO_PAGE = "Lectures";

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
        
//        int subjectid = Integer.parseInt(request.getParameter("txtSubjectID"));
        String url = QUIZ_INFO_PAGE;
        
        try {
            HttpSession session = request.getSession();
            UserAnswerDTO useranwserdto = (UserAnswerDTO) session.getAttribute("USER_SCORE");
            QuizDTO quizdto = (QuizDTO) session.getAttribute("CURRENT_QUIZ");
            
            //-------------DAO---------------------------------------------------------------------
            RegistrationDAO dao = new RegistrationDAO();
            TrackingProgressDAO trackingdao = new TrackingProgressDAO();
            LessonDetailsDAO lsdetaildao = new LessonDetailsDAO();
            LessonDAO lessondao = new LessonDAO();
            PackageDAO packdao = new PackageDAO();
            SubjectDAO subjectdao = new SubjectDAO();
            UsersDAO userdao = new UsersDAO();
            //---------------------------------------------------------------------------------------
            //lay quiz id tren session
            int quizid = useranwserdto.getQuizID();
            log("QuizID of Tracking PRogress 71 : " + quizid);
            //---------------------------------------------------------------------------------------
            //lay lessonid dua tren quizid vua lay
            
            int lessonID = lsdetaildao.getLessonIDbyQuizID(quizid);
            int subjectid = lessondao.getSubjectIDbyLessonID(lessonID);
            log("LessonID of Tracking Progress 77 : " + lessonID);
            log("SubjectID of Tracking Progress 78 : " + subjectid);
            //get userid
            String userID = useranwserdto.getUserID();
            log("UserID of Tracking Progress 69 : " + userID);
            String useremail = userdao.getUserEmailbyUserID(userID);
            //lay registration id dua tren userID va subjectID;
            int registrationID = dao.getRegistrationIDBySubIDandEmail(subjectid, useremail);
            log("Registration ID of Tracking Progress 72 : " + registrationID);
            //neu user pass quiz
            log("PASS RATE : "+quizdto.getPassRate());
            log("USER SCORE: "+useranwserdto.getScore() * 10);
            if (quizdto.getPassRate() <= (useranwserdto.getScore() * 10)) {                
                int packageid = dao.getPackageIDbyRegisIdSubIdUserId(registrationID, subjectid);
                //lay accessduration de tinh new deadline
                double accessduration = packdao.getAccessDuration(packageid);
                log("Access Duration of Tracking Progress 82 : " + accessduration);
                //lay lesson quiz list
                List<LessonDTO> list = lessondao.getCurrLessons(subjectid);
                List<LessonDTO> lessonlist = new ArrayList<>();
                for (LessonDTO lesson : list) {
                    if (lesson.getType().toLowerCase().equals("quiz")) {
                        lessonlist.add(lesson);
                    }
                }
                
                if (lessonlist.isEmpty()) {
                    log("Lesson Lsist is Null or Empty  in Tracking Progress 86 ");
                }
                //lay cac thong tin tu tracking progress cua specific user
                TrackingProgressDTO trackingdto = trackingdao.getTrackingProgressByRegistrationIDandLessonID(registrationID, lessonID);
                log("Current Tracking Progress: " + trackingdto.getRegistrationid() + " - " + trackingdto.getLessonid() + " - " + trackingdto.isStatus());
                if (trackingdto == null) {
                    log("Tracking DTO is Null in Tracking Progress 91 ");
                } else if (!trackingdto.isStatus()) {     
                    //set lai status cho tracking progress neu user pass quiz 
                    trackingdao.updateProgressStatus(registrationID, lessonID, true);
                    //lay old deadline tu tracking progress cu de tinh deadline moi 
                    Date olddaedline = trackingdto.getDeadline();
                    log("Old Deadline in Tracking Progress 94 : " + olddaedline);
                    //convert olddeadline sang miliseconds
                    long olddeadlinetime = olddaedline.getTime();
                    for (int i = 0; i < lessonlist.size(); i++) {
                        log("Lesson ID: " + lessonlist.get(i).getLessonID());
                        if (lessonlist.get(i).getLessonID() == lessonID) {
                            //lay lesson quiz id tiep theo tu list lesson quiz id
                            int newlessonid = lessonlist.get(i + 1).getLessonID();
                            log("New Lesson ID in Tracking Progress 102: " + newlessonid);
                            //lay so luong cac lesson quiz trong list lesson
                            int numberofQuiz = lessonlist.size();
                            log("Number of Quiz in Tracking Progress 105 : " + numberofQuiz);
                            //tinh new dadline 
                            long newdeadline = (long) ((((accessduration * 30 / numberofQuiz)) * 86400000) + olddeadlinetime);//old deadline time nay da chuyen sang milisecond
                            //convert new deadline sang sql date
                            java.sql.Date deadline = new java.sql.Date(newdeadline);

                            log("New Deadline in Tracking Progress : " + deadline);
                            //add moi cai lesson quiz id vua lay va set status la false
                            trackingdao.addnewTrackingProgress(registrationID, newlessonid, deadline, false);
                            break;
                        }
                    }
                } else {
                    log("current quiz has already passed!");
                }
                
                //reset ENABLED_QUIZZES
                //---------------------------------------------
                LessonDTO currLesson = (LessonDTO) session.getAttribute("CURRENT_LESSON");
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
                
                TrackingProgressDAO trackDAO = new TrackingProgressDAO();
                RegistrationDAO regDAO = new RegistrationDAO();
                int regID = regDAO.getRegistrationIDBySubIDandEmail(currLesson.getSubjectID(), currUser.getEmail());
                List<TrackingProgressDTO> trackList = trackDAO.getTrackingProgressByRegistrationID(regID);
                // User haven't done a single quiz
                List<LessonDTO> currLessons = (List<LessonDTO>) session.getAttribute("LESSONS_LIST");
                List<LessonDTO> currQuizzes = new ArrayList<>();
                for (LessonDTO l : currLessons) {
                    if (l.getType().equals("Quiz")) {
                        currQuizzes.add(l);
                    }
                }

                List<TrackingProgressDTO> orderedTrackList = new ArrayList<>();
                for (LessonDTO quiz : currQuizzes) {
                    for (TrackingProgressDTO track : trackList) {
                        if (track.getLessonid() == quiz.getLessonID()) {
                            orderedTrackList.add(track);
                            log("Current tracking progress: " + track.getRegistrationid() + " - " + track.getLessonid());
                        }
                    }
                }

                List<LessonDTO> enabledQuizzes = new ArrayList<>();

                if (trackList.size() == 1) {
                    // only the first quiz is available
                    enabledQuizzes.add(currQuizzes.get(0));
                } else {
                    for (TrackingProgressDTO track : orderedTrackList) {
                        if (track.isStatus()) {
                            for (LessonDTO quiz : currQuizzes) {
                                if (quiz.getLessonID() == track.getLessonid()) {
                                    enabledQuizzes.add(quiz);
                                    break;
                                }
                            }
                        } else {
                            for (LessonDTO quiz : currQuizzes) {
                                if (quiz.getLessonID() == track.getLessonid()) {
                                    enabledQuizzes.add(quiz);
                                    break;
                                }
                            }
                            // last available quiz
                            break;
                        }
                    }
                }

                session.setAttribute("ENABLED_QUIZZES", enabledQuizzes);
                //---------------------------------------------
            }
        } catch (SQLException ex) {
            log("Check TrackingProgressServlet SQL Exception - " + ex);
        } catch (NamingException ex) {
            log("Check TrackingProgressServlet NamingException - " + ex);
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
