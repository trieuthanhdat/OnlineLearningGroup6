package DAO.Post;

import DTO.Post.PostDTO;
import DTO.Post.PostDetailsDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class PostDAO implements Serializable {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    private void closeConnection() 
            throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    public PostDAO() 
            throws NamingException, SQLException {
        getAllPosts();
    }
    
    private List<PostDTO> postList = new ArrayList<>();

    public List<PostDTO> getPostList() {
        return postList;
    }        
    
    private void getAllPosts() 
        throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT PostID, PostCategoryID, Title, Thumbnail, BriefInfo, FeatureFlag, Status "
                           + "FROM Post";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int postID = rs.getInt("PostID");
                    int categoryID = rs.getInt("PostCategoryID");
                    String title = rs.getString("Title");
                    String thumbnail = rs.getString("Thumbnail");
                    String briefInfo = rs.getString("BriefInfo");
                    boolean featureFlag = rs.getBoolean("FeatureFlag");
                    boolean status = rs.getBoolean("Status");
                    
                    postList.add(new PostDTO(postID, categoryID, title, thumbnail, briefInfo, null, featureFlag, status));
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<PostDTO> getFeaturedPosts() {
        List<PostDTO> resultList = new ArrayList<>();
        
        for (PostDTO post : postList) {
            if (post.isStatus() && post.isFeatureFlag()) {
                resultList.add(post);
            }
        }
        
        return resultList;
    }
    
    public List<PostDTO> getFeaturedPostsByCategoryID(int categoryID, int currPostID) {
        List<PostDTO> resultList = new ArrayList<>();
        
        for (PostDTO post : postList) {
            if (post.isStatus() && post.isFeatureFlag()) {
                if (post.getCategoryID() == categoryID && post.getPostID() != currPostID) {
                    resultList.add(post);
                }
            }
        }
        
        return resultList;
    }
    
    public List<PostDTO> get5LatestPosts() {
        List<PostDTO> resultList = new ArrayList<>();
        
        if (postList.size() <= 5) {
            for (int i = 1; i <= postList.size(); i++) {
                resultList.add(postList.get(postList.size() - i ));
            }
        } else {
            for (int i = 1; i <= 5; i++) {
                resultList.add(postList.get(postList.size() - i ));
            }
        }
        
        return resultList;
    }
    
    public PostDTO getPostByID(int postID) {
        PostDTO postResult = null;
        for (PostDTO post : postList) {
            if (post.getPostID() == postID) {
                postResult = post;
            }
        }
        return postResult;
    }
    
    public PostDTO getPostDetails(PostDTO post) 
            throws NamingException, SQLException {               
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Author, Content, UpdateDate "
                           + "FROM PostDetails "
                           + "WHERE PostID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, post.getPostID());
                rs = stm.executeQuery();
                if (rs.next()) {
                    String author = rs.getNString("Author");
                    String content = rs.getNString("Content");
                    Date updateDate = rs.getDate("UpdateDate");
                    
                    post.setDetails(new PostDetailsDTO(author, content, updateDate));
                }
            }
        } finally {
            closeConnection();
        }
        
        return post;
    }
}
