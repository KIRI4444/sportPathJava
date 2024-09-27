package sportpath.dao.interfaces;

import sportpath.models.VerificationCode;

import java.time.LocalDateTime;

public interface VerificationDAO {
    void saveCode(String email, String code, LocalDateTime createdAt);
    VerificationCode findCodeByEmail(String email);
}
