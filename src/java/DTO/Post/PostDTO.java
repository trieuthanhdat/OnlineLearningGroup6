package DTO.Post;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class PostDTO implements Serializable {
    private int postID;
    private int categoryID;
    private String title;
    private String thumbnail;
    private String briefInfo;
    private PostDetailsDTO details;
    private boolean featureFlag;
    private boolean status;    

    public PostDTO() {
    }

    public PostDTO(int postID, int categoryID, String title, String thumbnail, String briefInfo, PostDetailsDTO details ,boolean featureFlag, boolean status) {
        this.postID = postID;
        this.categoryID = categoryID;
        this.title = title;
        this.thumbnail = thumbnail;
        this.briefInfo = briefInfo;
        this.featureFlag = featureFlag;
        this.status = status;
        this.details = details;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public boolean isFeatureFlag() {
        return featureFlag;
    }

    public void setFeatureFlag(boolean featureFlag) {
        this.featureFlag = featureFlag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PostDetailsDTO getDetails() {
        return details;
    }

    public void setDetails(PostDetailsDTO details) {
        this.details = details;
    }

   
}
