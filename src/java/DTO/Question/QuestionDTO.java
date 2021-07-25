package DTO.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QuestionDTO implements Serializable{
    private int questionID;
    private String mediaLink;
    private String content;
    private int lessonID;    
    private String level;
    private List<QuestionOptionDTO> options = new ArrayList<>();
    private String explanation;    
    private boolean status = true;

    public QuestionDTO() {
    }   
    
    public QuestionDTO(int questionID, String mediaLink, String content, int lessonID, String level, String explanation, boolean status) {
        this.questionID = questionID;
        this.mediaLink = mediaLink;
        this.content = content;
        this.lessonID = lessonID;
        this.level = level;        
        this.explanation = explanation;
        this.status = status;
    }    

    /**
     * @return the questionID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the mediaLink
     */
    public String getMediaLink() {
        return mediaLink;
    }

    /**
     * @param mediaLink the mediaLink to set
     */
    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
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
     * @return the lessonID
     */
    public int getLessonID() {
        return lessonID;
    }

    /**
     * @param lessonID the lessonID to set
     */
    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the options
     */
    public List<QuestionOptionDTO> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(List<QuestionOptionDTO> options) {
        this.options = options;
    }

    /**
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation the explanation to set
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
