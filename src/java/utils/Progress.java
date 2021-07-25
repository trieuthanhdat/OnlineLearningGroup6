/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Progress implements Serializable{
    private int numberOfLessonQuiz;
    private int numOfCompleteLessonQuiz;
    private int subjectID;

    public Progress() {
    }

    public Progress(int numberOfLessonQuiz, int numOfCompleteLessonQuiz, int subjectID) {
        this.numberOfLessonQuiz = numberOfLessonQuiz;
        this.numOfCompleteLessonQuiz = numOfCompleteLessonQuiz;
        this.subjectID = subjectID;
    }

    /**
     * @return the numberOfLessonQuiz
     */
    public int getNumberOfLessonQuiz() {
        return numberOfLessonQuiz;
    }

    /**
     * @param numberOfLessonQuiz the numberOfLessonQuiz to set
     */
    public void setNumberOfLessonQuiz(int numberOfLessonQuiz) {
        this.numberOfLessonQuiz = numberOfLessonQuiz;
    }

    /**
     * @return the numOfCompleteLessonQuiz
     */
    public int getNumOfCompleteLessonQuiz() {
        return numOfCompleteLessonQuiz;
    }

    /**
     * @param numOfCompleteLessonQuiz the numOfCompleteLessonQuiz to set
     */
    public void setNumOfCompleteLessonQuiz(int numOfCompleteLessonQuiz) {
        this.numOfCompleteLessonQuiz = numOfCompleteLessonQuiz;
    }

    /**
     * @return the subjectID
     */
    public int getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
    
    
}
