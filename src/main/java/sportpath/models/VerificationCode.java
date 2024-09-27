package sportpath.models;

import java.time.LocalDateTime;

public class VerificationCode {
    private String email;
    private String code;
    private LocalDateTime createdAt;

    public VerificationCode(String email, String code, LocalDateTime createdAt) {
        this.email = email;
        this.code = code;
        this.createdAt = createdAt;
    }

    public VerificationCode() {

    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
