package Servlet.Quiz;


import DAO.Quiz.QuizOptionDAO;
import DAO.Question.QuestionDAO;
import DAO.Quiz.QuizQuestionDAO;
import DAO.Quiz.QuizDAO;
import DTO.Question.QuestionDTO;
import DTO.Question.QuizQuestionDTO;
import DTO.Quiz.QuizDTO;
import DTO.Quiz.QuizErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MakeQuizServlet", urlPatterns = {"/MakeQuizServlet"})
public class MakeQuizServlet extends HttpServlet {

    private final static String QUIZ_LIST_PAGE = "GetSubjectForUpdatingServlet";
    private final static String ERROR = "error.jsp";

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
            throws ServletException, IOException, ClassNotFoundException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = ERROR;
        QuestionDAO questionDAO = new QuestionDAO();
        QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
        QuizOptionDAO quizOptionDAO = new QuizOptionDAO();
        QuizErrorDTO error = new QuizErrorDTO();

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String name = request.getParameter("name");
        int numOfQuestions = Integer.parseInt(request.getParameter("numOfQuestions"));
        int Duration = Integer.parseInt(request.getParameter("duration"));
        double passRate = Double.parseDouble(request.getParameter("passRate"));
        String level = request.getParameter("level");
        int lessonID = Integer.parseInt(request.getParameter("lessonID"));

//        log("subjectID: "+subjectID) ;
//        log("name: "+name) ;
//        log("numOfQuestions: "+numOfQuestions) ;
//        log("Duration: "+Duration) ;
//        log("passRate: "+passRate) ;
//        log("level: "+level) ;
//        log("lessonID: "+lessonID) ;
        
        
        
        try {
            boolean isError = true;
            questionDAO.importQuestion(lessonID);//lấy question thuộc lesson đó ra
            List<QuestionDTO> question = questionDAO.getQuestion();

            if (name.length() < 5 || name.length() > 50) { // name qúa ngắn hay quá dài
                error.setInvalidQuizName("Name must be 5-50 characters");
                isError = false;
//                log("Name must be 5-50 characters");
            }

            if (question.size() < numOfQuestions) { // Nếu số lượng câu hỏi không khớp với lại số lượng yêu cầu
                error.setQuestionShortage("Not enough question. Please add more!");
                isError = false;
//                log("Not enough question. Please add more");
            }

            if (quizQuestionDAO.randomQuestion(question, level, numOfQuestions, 0) == false) { // Nếu số lượng câu theo level được đảm bảo
                error.setQuestionLevelShortage("Not enough question for that level");
                isError = false;
//                log("Not enough question for that level");
            }
            
            if (passRate > 80 || passRate < 50) {// pass rate vô lí
                error.setUnreasonablePassRate("Pass rate must not lower than 50% and higher than 80%");
                isError = false;
//                log("Pass rate must not lower than 50% and higher than 80%");
            }
            
            if (numOfQuestions > Duration) {
                error.setUnreasonableTime("Each question must have at least 1 minute");
//                log("Each question must have at least 1 minute");
            }
            
            if (isError) {
                QuizDTO quiz = new QuizDTO(0, subjectID, name, numOfQuestions, Duration, passRate, level, false);

                QuizDAO quizDAO = new QuizDAO();
                boolean result1 = quizDAO.createQuiz(quiz);// tạo quiz
                int quizID = quizDAO.getQuizListSize(); // query lại để lấy quizID
                log("Result 1: "+result1);
                if (result1) {//tạo thành công copy question
                    //truyền vào quiz để phân loại và copy
                    boolean result2 = quizQuestionDAO.randomQuestion(question, level, numOfQuestions, quizID);
                    log("Result 2: "+result2);
                    if (result2) {// random ko thiếu câu nào
                        boolean result3 = quizQuestionDAO.exportQuizQuestion();
                        quizQuestionDAO.importQuizQuestion(quizID); //query lại để lấy questionNo
                        List<QuizQuestionDTO> quizQuestion = quizQuestionDAO.getQuizQuestionList();
                        log("Result 3: "+result3);
                        if (result3) {// nếu câu hỏi được thêm vào thành công, lấy option theo từng câu hỏi
                            quizOptionDAO.getQuizOption(quizQuestion);
                            boolean result4 = quizOptionDAO.exportOption();
                            log("Result 4: "+result4);
                            if (result4) {
                                url = QUIZ_LIST_PAGE+"?txtSubjectID="+subjectID;
                            } else {
                                quizDAO.removeQuiz(quizID);
                                quizQuestionDAO.removeQuestionByQuiz(quizID);
                                quizOptionDAO.removeOption(quizQuestion);
                                error.setCreateError("Some issue happen and prevent quiz from being create");
                                request.setAttribute("CREATE_QUIZ_ERROR", error);
//                                log(""+error);
                            }
                        } else {
                            quizDAO.removeQuiz(quizID);
                            quizQuestionDAO.removeQuestionByQuiz(quizID);
                            error.setCreateError("Some issue happen and prevent quiz from being create");
                            request.setAttribute("CREATE_QUIZ_ERROR", error);
//                            log(""+error);
                        }
                    } else {
                        quizDAO.removeQuiz(quizID);
                        error.setCreateError("Some issue happen and prevent quiz from being create");
                        request.setAttribute("CREATE_QUIZ_ERROR", error);
//                        log(""+error);
                    }
                }
            } else {
                request.setAttribute("CREATE_QUIZ_ERROR", error);
                
            }

        } catch (NamingException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
