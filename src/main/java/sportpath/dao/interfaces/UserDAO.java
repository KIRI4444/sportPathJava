package sportpath.dao.interfaces;

import sportpath.models.User;

public interface UserDAO {
    void save(User user);
    int checkMailAndUsername(String username, String mail);
    int getUserId(String username);
}
