package sportpath.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sportpath.ApiResponse;
import sportpath.services.VerificationService;

@RestController
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendVerification(@RequestParam String email) {
        try {
            verificationService.generateAndSendCode(email);
            return ResponseEntity.ok(new ApiResponse(200, "Verification code sent successfully", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse(500, "Error sending verification code", null));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyCode(@RequestParam String email, @RequestParam String code) {
        if (verificationService.verifyCode(email, code)) {
            //Валидный код
            return ResponseEntity.ok(new ApiResponse(200, "Verification code verified successfully", null));
        } else {
            return ResponseEntity.ok(new ApiResponse(500, "Error verifying code", null));
        }
    }
}
