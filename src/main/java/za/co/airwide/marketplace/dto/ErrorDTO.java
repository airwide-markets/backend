package za.co.airwide.marketplace.dto;

import java.io.Serializable;

/*
'type' => "LOGIN_ERROR", 'message' => 'Account  not  updated'
 */
public class ErrorDTO implements Serializable {
    private boolean success;
    private String type;
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(String type, String message) {
        this(false, type, message);
    }
    public ErrorDTO(boolean success, String type, String message) {
        this.success = success;
        this.type = type;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
