package sportpath.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sportpath.dao.VerificationDAOImpl;
import sportpath.models.VerificationCode;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class VerificationService {

    private final VerificationDAOImpl verificationDAOImpl;
    private final JavaMailSender mailSender;

    private final Random random = new Random();

    @Autowired
    public VerificationService(VerificationDAOImpl verificationDAOImpl, JavaMailSender mailSender) {
        this.verificationDAOImpl = verificationDAOImpl;
        this.mailSender = mailSender;
    }

    public void generateAndSendCode(String email) {
        String code = String.format("%06d", random.nextInt(999999));
        LocalDateTime createdAt = LocalDateTime.now();

        verificationDAOImpl.saveCode(email, code, createdAt);
        System.out.println("Verification code: " + code);
        sendVerificationCode(email, code);
    }

    private void sendVerificationCode(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kirill63333@yandex.ru");
        message.setTo(email);
        message.setSubject("Код подтверждения региастрации");
        message.setText("Ваш код подтверждения (действителен 30 минут): " + code);
        mailSender.send(message);
    }

    public boolean verifyCode(String email, String userCode) {
        VerificationCode storedCode = verificationDAOImpl.findCodeByEmail(email);

        if (storedCode == null || !storedCode.getCode().equals(userCode)) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(storedCode.getCreatedAt().plusMinutes(30));
    }
}
