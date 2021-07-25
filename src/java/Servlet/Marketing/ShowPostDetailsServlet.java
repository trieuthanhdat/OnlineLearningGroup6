package Servlet.Marketing;

import DAO.Post.PostDAO;
import DTO.Post.PostDTO;
import java.io.IOException;
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
@WebServlet(name = "ShowPostDetailsServlet", urlPatterns = {"/ShowPostDetailsServlet"})
public class ShowPostDetailsServlet extends HttpServlet {

    private final String ERROR_PAGE = "ErrorPage";
    private final String POST_DETAILS_PAGE = "PostDetails.jsp";
    
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
            if (request.getParameter("txtPostID") != null) {
                int postID = Integer.parseInt(request.getParameter("txtPostID"));
                PostDAO dao = new PostDAO();
                PostDTO currPost = dao.getPostByID(postID);
                if (currPost != null) {
                    currPost = dao.getPostDetails(currPost);
                    List<PostDTO> postOfCategory = dao.getFeaturedPostsByCategoryID(currPost.getCategoryID(), currPost.getPostID());
                    log("POSTS_OF_CATEGORY SIZE: " + String.valueOf(postOfCategory.size()));
                    
                    request.setAttribute("CURRENT_POST", currPost);
                    request.setAttribute("POSTS_OF_CATEGORY", postOfCategory);                    
                    url = POST_DETAILS_PAGE;
                }                                                
            }
        } catch (NamingException | NumberFormatException | SQLException ex) {
            log(ex.toString());
        } finally {
            if (url.equals(ERROR_PAGE)) {
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
