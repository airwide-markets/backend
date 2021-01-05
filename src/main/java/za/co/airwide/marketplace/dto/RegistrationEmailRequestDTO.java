package za.co.airwide.marketplace.dto;

public class RegistrationEmailRequestDTO {

    private String userToken;
    private String emailAddress;
    private String token;
    private String resend;

    public RegistrationEmailRequestDTO() {
    }

    public RegistrationEmailRequestDTO( String userToken,
                                        String emailAddress,
                                        String token,
                                        String resend) {
        this.userToken = userToken;
        this.emailAddress = emailAddress;
        this.token = token;
        this.resend = resend;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResend() {
        return resend;
    }

    public void setResend(String resend) {
        this.resend = resend;
    }
}
