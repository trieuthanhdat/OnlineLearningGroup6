/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.TrackingProgress;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class TrackingProgressDTO implements Serializable {

    private int registrationid;
    private int lessonid;
    private Date deadline;
    private boolean status;
    private int subjectid;

    public TrackingProgressDTO() {
    }

    public TrackingProgressDTO(int registrationid, int lessonid, Date deadline, boolean status, int subjectid) {
        this.registrationid = registrationid;
        this.lessonid = lessonid;
        this.deadline = deadline;
        this.status = status;
        this.subjectid = subjectid;
    }

    /**
     * @return the registrationid
     */
    public int getRegistrationid() {
        return registrationid;
    }

    /**
     * @param registrationid the registrationid to set
     */
    public void setRegistrationid(int registrationid) {
        this.registrationid = registrationid;
    }

    /**
     * @return the lessonid
     */
    public int getLessonid() {
        return lessonid;
    }

    /**
     * @param lessonid the lessonid to set
     */
    public void setLessonid(int lessonid) {
        this.lessonid = lessonid;
    }

    /**
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    /**
     * @return the subjectid
     */
    public int getSubjectid() {
        return subjectid;
    }

    /**
     * @param subjectid the subjectid to set
     */
    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

}
