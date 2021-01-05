package za.co.airwide.marketplace.dto;

/**
 * array('msisdn' => $this->getMsisdn(), 'password' => $this->getPassword())
 */
public class AuthenticationRequestDTO {

    private String msisdn;
    private String password;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(
                String msisdn,
                String password) {
        this.msisdn = msisdn;
        this.password = password;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
