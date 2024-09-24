package sportpath.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sportpath.ApiResponse;
import sportpath.BCrypt.PasswordUtils;
import sportpath.dao.UserDAOImpl;
import sportpath.models.User;

@Service
public class LoginValidationService {

    private final UserDAOImpl userDAOImpl;
    private final PasswordUtils passwordUtils;
    public int loginStatus;
    public String hashPassword;

    @Autowired
    public LoginValidationService(UserDAOImpl userDAOImpl, PasswordUtils passwordUtils) {
        this.userDAOImpl = userDAOImpl;
        this.passwordUtils = passwordUtils;
    }

    public ResponseEntity<ApiResponse> isDataValid(User user) {
        // Проверка на валидность данных
        // -2 - внутренняя ошибка сервера
        // -1 - username не существует
        // 0 - один из параметров null/пустой
        // 1 - введенный пароль не удовлетворяет условиям
        // 2 - неверный пароль от аккаунта
        // 3 - успешная аутентификация

        String username = user.getUsername();
        String password = user.getPassword();

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            loginStatus = 0;
            return ResponseEntity.ok(new ApiResponse(loginStatus, "one of param is null", ""));
        }

        if (password.length() < 8 || password.length() > 40) {
            loginStatus = 1;
            return ResponseEntity.ok(new ApiResponse(loginStatus, "password format is incorrect", ""));
        }

        try {
            hashPassword = userDAOImpl.getPasswordHashByUsername(username);
        } catch (EmptyResultDataAccessException e) {
            loginStatus = -1;
            return ResponseEntity.ok(new ApiResponse(loginStatus, "username does not exist", ""));
        } catch (Exception e) {
            loginStatus = -2;
            return ResponseEntity.ok(new ApiResponse(loginStatus, "iternal server error", ""));
        }

        if (passwordUtils.checkPassword(password, hashPassword)) {
            loginStatus = 3;
            return ResponseEntity.ok(new ApiResponse(loginStatus, "success login", ""));
        } else {
            loginStatus = 2;
            return ResponseEntity.ok(new ApiResponse(loginStatus, "wrong passowrd", ""));
        }
    }
}
