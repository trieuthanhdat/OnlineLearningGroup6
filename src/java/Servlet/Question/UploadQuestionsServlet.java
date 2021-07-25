package Servlet.Question;

import DAO.Lesson.LessonDAO;
import DAO.Subject.SubjectDAO;
import DTO.Lesson.LessonDTO;
import DTO.Question.QuestionDTO;
import DTO.Question.QuestionOptionDTO;
import DTO.Subject.SubjectDTO;
import DTO.User.UserDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.FileHelpers;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UploadQuestions", urlPatterns = {"/UploadQuestions"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
   maxFileSize = 1024 * 1024 * 5,
   maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadQuestionsServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String ERROR_PAGE = "ImportQuestions";
    private final String RESULT_PAGE = "ImportQuestions";
    private final String UPLOAD_DIRECTORY = "FileUpload";
    private final int ASCII_OF_A = 65;
    private final int MAX_OPTIONS_SIZE = 10;
    
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
                if (currUser != null && (currUser.getRole().equals("Admin") || currUser.getRole().equals("Expert"))) {
                    String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    log("Upload Path: " + uploadPath);

                    String filePath = "";
                    for (Part part : request.getParts()) {
                        String fileName = FileHelpers.getFileName(part);
                        if (fileName.endsWith(".txt")) {
                            filePath = uploadPath + File.separator + fileName;
                            log("File Path: " + filePath);
                            part.write(filePath);
                        } else {
                            url = ERROR_PAGE;
                            log("UploadServlet: Wrong file's extension (must be .txt): " + fileName);
                        }
                    }

                    if (!filePath.isEmpty()) {
                        FileInputStream fis = new FileInputStream(filePath);
                        InputStreamReader fr = new InputStreamReader(fis, "utf-8");
                        BufferedReader br = new BufferedReader(fr);

                        LessonDAO lessonDAO = new LessonDAO();
                        SubjectDAO subDAO = new SubjectDAO();
                        List<QuestionDTO> newQuestionsList = new ArrayList<>();

                        QuestionDTO currQuestion = null;
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            if (line.trim().toLowerCase().startsWith("question")) {
                                log("begin of question");
                                currQuestion = new QuestionDTO();
                            } else if (line.startsWith("-")) {
                                if (currQuestion != null) {
                                    //remove '-' symbol
                                    line = line.substring(1).trim();
                                    //find index of ':'
                                    int seperatorIndex = line.indexOf(":");
                                    String property = line.substring(0, seperatorIndex);
                                    String value = line.substring(seperatorIndex + 1).trim();
                                    switch (property.toLowerCase()) {
                                        case "content":
                                            if (!value.isEmpty()) {
                                                currQuestion.setContent(value);
                                            } else {
                                                currQuestion.setStatus(false);
                                            }
                                            break;
                                        case "medialink":
                                            if (!value.isEmpty()) {
                                                currQuestion.setMediaLink(value);
                                            }
                                            break;
                                        case "lessonid":
                                            try {
                                                int intValue = Integer.parseInt(value);
                                                List<SubjectDTO> availableSubList = subDAO.getAvailableSubjectList(currUser.getUserID(), currUser.getRole());
                                                List<LessonDTO> availableLessonList = new ArrayList<>();
                                                
                                                for (SubjectDTO sub : availableSubList) {
                                                    List<LessonDTO> currSubLessons = lessonDAO.getLessonsBySubjectID(sub.getSubjectID());
                                                    for (LessonDTO l : currSubLessons) {
                                                        if (l.getType().equals("Lesson")) {
                                                            availableLessonList.add(l);
                                                        }
                                                    }
                                                }
                                                
                                                boolean valid = false;
                                                for (LessonDTO l : availableLessonList) {
                                                    if (l.getLessonID() == intValue) {
                                                        valid = true;
                                                    }
                                                }
                                                
                                                currQuestion.setStatus(valid);
                                                currQuestion.setLessonID(intValue);
                                            } catch (NumberFormatException ex) {
                                                currQuestion.setStatus(false);
                                            }
                                            break;
                                        case "level":
                                            if (!value.isEmpty()) {
                                                if (value.toLowerCase().equals("easy") || value.toLowerCase().equals("medium") || value.toLowerCase().equals("hard")) {
                                                    currQuestion.setLevel(value.toLowerCase());
                                                } else {
                                                    currQuestion.setStatus(false);
                                                }
                                            } else {
                                                currQuestion.setStatus(false);
                                            }
                                            break;
                                        case "options":
                                            String values[] = value.split("~");
                                            if (values.length != 0) {
                                                for (int i = 0; i < values.length; i++) {
                                                    values[i] = values[i].trim();
                                                    boolean answerStatus = false;
                                                    int optionSeperatorIndex = values[i].indexOf("/");
                                                    if (optionSeperatorIndex < 0) {
                                                        answerStatus = true;
                                                        optionSeperatorIndex = values[i].indexOf("\\");

                                                        if (optionSeperatorIndex < 0) {
                                                            currQuestion.setStatus(false); // wrong format for options of new question
                                                            break;
                                                        }
                                                    }
                                                    // take value of option after "." symbol | ex: A.first option
                                                    String optionValue = values[i].substring(optionSeperatorIndex + 1).trim();

                                                    List<QuestionOptionDTO> options = currQuestion.getOptions();
                                                    if (options.isEmpty()) {
                                                        char optionLetter = (char) ASCII_OF_A;
                                                        options.add(new QuestionOptionDTO(0, optionValue, String.valueOf(optionLetter), answerStatus));
                                                    } else if (options.size() <= MAX_OPTIONS_SIZE) {
                                                        char optionLetter = (char) (ASCII_OF_A + options.size());
                                                        options.add(new QuestionOptionDTO(0, optionValue, String.valueOf(optionLetter), answerStatus));
                                                    }
                                                }
                                            } else {
                                                currQuestion.setStatus(false);
                                            }
                                            break;
                                        case "explanation":
                                            if (!value.isEmpty()) {
                                                currQuestion.setExplanation(value);
                                            } else {
                                                currQuestion.setStatus(false);
                                            }
                                            break;
                                        default:
                                            currQuestion = null; // wrong format current question is invalid
                                    }
                                } else {
                                    break; //break parse file loop if text file doesn't follow template
                                }
                            } else if (line.startsWith("#####")) {
                                log("end of question");
                                if (currQuestion != null) {
                                    newQuestionsList.add(currQuestion);
                                    currQuestion = null;
                                } else {
                                    break; //break parse file loop if text file doesn't follow template
                                }
                            }
                        } // end while loop

                        br.close();
                        fr.close();
                        fis.close();

                        File currFile = new File(filePath);
                        currFile.delete();
                        
                        session.setAttribute("NEW_QUESTION_LIST", newQuestionsList);     
                        url = RESULT_PAGE;
                    }
                }
            }        
        } catch (Exception ex) {
            ex.printStackTrace();
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
