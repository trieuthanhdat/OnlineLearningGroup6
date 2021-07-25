package Error;

/**
 *
 * @author ASUS
 */
public class LoginError {
    private String wrongEmailFormatError;
    private String wrongPasswordError;    
    private String loginEmail;
    private String loginPassword;
    
    public LoginError() {
    }   

    /**
     * @return the wrongEmailFormatError
     */
    public String getWrongEmailFormatError() {
        return wrongEmailFormatError;
    }

    /**
     * @param wrongEmailFormatError the wrongEmailFormatError to set
     */
    public void setWrongEmailFormatError(String wrongEmailFormatError) {
        this.wrongEmailFormatError = wrongEmailFormatError;
    }

    /**
     * @return the wrongPasswordError
     */
    public String getWrongPasswordError() {
        return wrongPasswordError;
    }

    /**
     * @param wrongPasswordError the wrongPasswordError to set
     */
    public void setWrongPasswordError(String wrongPasswordError) {
        this.wrongPasswordError = wrongPasswordError;
    }

    /**
     * @return the loginEmail
     */
    public String getLoginEmail() {
        return loginEmail;
    }

    /**
     * @param loginEmail the loginEmail to set
     */
    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    /**
     * @return the loginPassword
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * @param loginPassword the loginPassword to set
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
