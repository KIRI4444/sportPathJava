package sportpath.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sportpath.ApiResponse;
import sportpath.BCrypt.PasswordUtils;
import sportpath.dao.UserDAOImpl;
import sportpath.models.User;

@Service
public class UserRegistrationService {

    private final UserDAOImpl userDAOImpl;
    private final PasswordUtils passwordUtils;

    @Autowired
    public UserRegistrationService(UserDAOImpl userDAOImpl, PasswordUtils passwordUtils) {
        this.userDAOImpl = userDAOImpl;
        this.passwordUtils = passwordUtils;
    }

    public ResponseEntity<ApiResponse> registerUser(User user, int status) {
        if (status == 0) {
             return ResponseEntity.ok(new ApiResponse(status, "one of param is null", ""));
        }

        if (status == 1) {
            return ResponseEntity.ok(new ApiResponse(status, "password is incorrect", ""));
        }

        if (status == 2) {
            return ResponseEntity.ok(new ApiResponse(status, "user already exists", ""));
        }

        String hashedPassword = passwordUtils.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userDAOImpl.save(user);
        int userId = userDAOImpl.getUserId(user.getUsername());
        String data = String.valueOf((userId));
        return ResponseEntity.ok(new ApiResponse(status, "success", data));
    }
}
