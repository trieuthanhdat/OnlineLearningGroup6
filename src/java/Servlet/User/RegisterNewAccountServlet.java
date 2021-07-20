package Servlet.User;

import Error.RegisterNewAccountError;
import Temp.UsersDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Mail;
import utils.NewAccount;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RegisterNewAccountServlet", urlPatterns = {"/RegisterNewAccountServlet"})
public class RegisterNewAccountServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String VALIDATION_PAGE = "AccountConfirmation";
    private final int VALIDATION_NUM_SIZE = 6;
    private final long AVAILABLE_TIME_LIMIT_FOR_NEW_ACCOUNT = 300000; //300000 millisecs = 5 minutes

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
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirmPassword");
            String fullName = request.getParameter("txtFullName");

            RegisterNewAccountError errors = new RegisterNewAccountError();
            boolean foundErr = false;
            
            errors.setRegisterEmail(email);
            errors.setRegisterFullName(fullName);
            errors.setRegisterPassword(password);
            errors.setRegisterConfirm(confirm);
            
            if (!email.trim().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                errors.setWrongEmailFormatErr("Wrong email format! (example: abcdef@gmail.com)");
                foundErr = true;
            }
            
            if (fullName.trim().length() < 5 && fullName.trim().length() > 50) {                
                errors.setFullNameLengthErr("Full name is required input from 5 to 50 characters.");
                foundErr = true;
            }
            
            if (password.trim().length() < 5 && password.trim().length() > 50) {
                errors.setFullNameLengthErr("Password is required input from 5 to 50 characters.");
                foundErr = true;
            }
            
            if (!confirm.trim().equals(password.trim())) {
                errors.setConfirmNotMatchErr("Confirm password doesn't match provided password.");
                foundErr = true;
            }
            
            if (!foundErr) {
                boolean result = false;
                UsersDAO dao = new UsersDAO();
                //check duplicated email in DB
                result = dao.checkExistUserEmail(email);
                
                if (!result) {
                    String fileName = "NewAccount.txt";
                    String filePath = getServletContext().getRealPath("WEB-INF" + File.separator + fileName);
                    log("RegisterNewAccountServlet: " + filePath);

                    File newAccountFile = new File(filePath);
                    FileReader fr = new FileReader(newAccountFile);
                    BufferedReader br = new BufferedReader(fr);

                    List<NewAccount> pendingAccs = new ArrayList<>();
                    NewAccount currAcc = null;
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            String newAccountInfo[] = line.split("~");
                            String currEmail = newAccountInfo[0];
                            String currPassword = newAccountInfo[1];
                            String currFullname = newAccountInfo[2];
                            String validationNums = newAccountInfo[3];
                                 LocalDateTime createTime = LocalDateTime.parse(newAccountInfo[4]);

                            currAcc = new NewAccount(currEmail, currPassword, currFullname, validationNums, createTime);
                            pendingAccs.add(currAcc);
                        }
                    }
                    br.close();
                    fr.close();
                    // remove expired pending new account
                    Iterator<NewAccount> accIte = pendingAccs.iterator();
                    boolean duplicateInFile = false;
                    while (accIte.hasNext()) {
                        currAcc = accIte.next();
                        if (currAcc.getEmail().equals(email)) {
                            duplicateInFile = true;
                            //url = ?
                        }
                        // check if create time is invalid then remove pending accs (under 5 minutes since create pending new account)                    
                        ZonedDateTime zdt = currAcc.getCreateTime().atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
                        long createTimeInMilli = zdt.toInstant().toEpochMilli();
                        if ((System.currentTimeMillis() - createTimeInMilli) <= 0
                                && (System.currentTimeMillis() - createTimeInMilli) > AVAILABLE_TIME_LIMIT_FOR_NEW_ACCOUNT) {
                            accIte.remove();
                            log("Acc deleted");
                        }
                    }
                    // no duplicate in both file and database then add the new account to the list
                    if (!duplicateInFile) {
                        Random rd = new Random();
                        String validationNums = "";
                        for (int i = 0; i < VALIDATION_NUM_SIZE; i++) {
                            validationNums += String.valueOf(rd.nextInt(10));
                        }

                        NewAccount acc = new NewAccount(email, password, fullName, validationNums, LocalDateTime.now());

                        pendingAccs.add(acc);
                        Mail.SendEmail(email, validationNums);

                        url = VALIDATION_PAGE;
                    }

                    // put the new account list back to the file
                    FileOutputStream fos = new FileOutputStream(newAccountFile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    BufferedWriter bw = new BufferedWriter(osw);

                    for (NewAccount acc : pendingAccs) {
                        bw.write(acc.getEmail() + "~" + acc.getPassword() + "~" + acc.getFullName() + "~" + acc.getValidationNums()
                                + "~" + acc.getCreateTime().toString());
                        bw.newLine();
                    }
                    bw.close();
                    osw.close();
                    fos.close();
                    
                    HttpSession session = request.getSession();
                    session.removeAttribute("REGISTER_ERROR");
                } else {
                    errors.setDuplicateEmailErr("There's already an account with that email in the system!");
                    foundErr = true;
                }                
            } 
            
            if (foundErr) {
                HttpSession session = request.getSession();
                session.setAttribute("REGISTER_ERROR", errors);
            }
        } catch (MessagingException | SQLException | NamingException ex) {
            log(ex.toString());
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
