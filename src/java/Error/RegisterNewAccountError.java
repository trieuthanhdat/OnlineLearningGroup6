package Error;

/**
 *
 * @author ASUS
 */
public class RegisterNewAccountError {
    private String wrongEmailFormatErr;
    private String fullNameLengthErr;    
    private String passwordLengthErr;
    private String confirmNotMatchErr;  
    private String duplicateEmailErr;
    private String registerEmail;
    private String registerFullName;
    private String registerPassword;
    private String registerConfirm;

    public RegisterNewAccountError() {
    }

    /**
     * @return the wrongEmailFormatErr
     */
    public String getWrongEmailFormatErr() {
        return wrongEmailFormatErr;
    }

    /**
     * @param wrongEmailFormatErr the wrongEmailFormatErr to set
     */
    public void setWrongEmailFormatErr(String wrongEmailFormatErr) {
        this.wrongEmailFormatErr = wrongEmailFormatErr;
    }

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the confirmNotMatchErr
     */
    public String getConfirmNotMatchErr() {
        return confirmNotMatchErr;
    }

    /**
     * @param confirmNotMatchErr the confirmNotMatchErr to set
     */
    public void setConfirmNotMatchErr(String confirmNotMatchErr) {
        this.confirmNotMatchErr = confirmNotMatchErr;
    }

    /**
     * @return the duplicateEmailErr
     */
    public String getDuplicateEmailErr() {
        return duplicateEmailErr;
    }

    /**
     * @param duplicateEmailErr the duplicateEmailErr to set
     */
    public void setDuplicateEmailErr(String duplicateEmailErr) {
        this.duplicateEmailErr = duplicateEmailErr;
    }

    /**
     * @return the registerEmail
     */
    public String getRegisterEmail() {
        return registerEmail;
    }

    /**
     * @param registerEmail the registerEmail to set
     */
    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    /**
     * @return the registerFullName
     */
    public String getRegisterFullName() {
        return registerFullName;
    }

    /**
     * @param registerFullName the registerFullName to set
     */
    public void setRegisterFullName(String registerFullName) {
        this.registerFullName = registerFullName;
    }

    /**
     * @return the registerPassword
     */
    public String getRegisterPassword() {
        return registerPassword;
    }

    /**
     * @param registerPassword the registerPassword to set
     */
    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    /**
     * @return the registerConfirm
     */
    public String getRegisterConfirm() {
        return registerConfirm;
    }

    /**
     * @param registerConfirm the registerConfirm to set
     */
    public void setRegisterConfirm(String registerConfirm) {
        this.registerConfirm = registerConfirm;
    }

}
