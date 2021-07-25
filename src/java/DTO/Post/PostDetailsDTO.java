package DTO.Post;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class PostDetailsDTO implements Serializable {    
    private String author;
    private String content;
    private Date UpdateDate;

    public PostDetailsDTO() {
    }

    public PostDetailsDTO(String author, String content, Date UpdateDate) {
        this.author = author;
        this.content = content;
        this.UpdateDate = UpdateDate;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the UpdateDate
     */
    public Date getUpdateDate() {
        return UpdateDate;
    }

    /**
     * @param UpdateDate the UpdateDate to set
     */
    public void setUpdateDate(Date UpdateDate) {
        this.UpdateDate = UpdateDate;
    }
    
}
