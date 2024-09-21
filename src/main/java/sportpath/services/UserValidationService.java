package sportpath.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sportpath.ApiResponse;
import sportpath.dao.UserDAOImpl;
import sportpath.models.User;

@Service
public class UserValidationService {

    private final UserDAOImpl userDAOImpl;

    @Autowired
    public UserValidationService(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    public int isDataValid(User user) {
        // Проверка на валидность данных
        // 0 - один из параметров null/пустой
        // 1 - пароль не соответствует требованиям
        // 2 - почта/логин уже заняты
        // 3 - успешная валидация

        String username = user.getUsername();
        String password = user.getPassword();
        String mail = user.getMail();


        if (username == null || password == null || mail == null || username.length() == 0 || password.length() == 0 || mail.length() == 0) {
            return 0;
        }

        if (password.length() < 8 || password.length() > 40) {
            return 1;
        }

        int check = userDAOImpl.checkMailAndUsername(username, mail);

        if (check != 0) {
            return 2;
        }

        return 3;
    }
}
