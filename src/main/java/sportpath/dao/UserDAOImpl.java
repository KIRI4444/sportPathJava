package sportpath.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sportpath.dao.interfaces.UserDAO;
import sportpath.models.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate JdbcTemplate;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.JdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username, mail, password) VALUES (?,?,?)";
        JdbcTemplate.update(sql, user.getUsername(), user.getMail(), user.getPassword());
    }

    public int checkMailAndUsername(String username, String mail) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? OR mail = ?";
        return JdbcTemplate.queryForObject(sql, new Object[]{username, mail}, Integer.class);
    }

    public int getUserId(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        return JdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
    }

    public String getPasswordHashByUsername(String username) {
        String sql = "SELECT password FROM users WHERE username = ?";
        return JdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
    }
}
