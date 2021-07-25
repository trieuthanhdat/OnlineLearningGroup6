package DTO.Slider;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class SliderDTO implements Serializable {
    private int sliderID;   
    private String title;
    private String backlink;
    private String image;
    private String notes;
    private boolean status;

    public SliderDTO() {
    }

    public SliderDTO(int sliderID, String title, String backlink, String image, String notes, boolean status) {
        this.sliderID = sliderID;
        this.title = title;
        this.backlink = backlink;
        this.image = image;
        this.notes = notes;
        this.status = status;
    }

    /**
     * @return the sliderID
     */
    public int getSliderID() {
        return sliderID;
    }

    /**
     * @param sliderID the sliderID to set
     */
    public void setSliderID(int sliderID) {
        this.sliderID = sliderID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the backlink
     */
    public String getBacklink() {
        return backlink;
    }

    /**
     * @param backlink the backlink to set
     */
    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
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
