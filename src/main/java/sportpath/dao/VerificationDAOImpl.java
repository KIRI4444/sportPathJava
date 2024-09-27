package sportpath.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sportpath.dao.interfaces.VerificationDAO;
import sportpath.models.CourtOnline;
import sportpath.models.VerificationCode;

import java.time.LocalDateTime;

@Repository
public class VerificationDAOImpl implements VerificationDAO {

    private final JdbcTemplate JdbcTemplate;

    @Autowired
    public VerificationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.JdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCode(String email, String code, LocalDateTime createdAt) {
        String sql = "INSERT INTO verification_codes (email, code, created_at) VALUES (?, ?, ?)";
        JdbcTemplate.update(sql, email, code, createdAt);
    }

    @Override
    public VerificationCode findCodeByEmail(String email) {
        String sql = "SELECT code, created_at FROM verification_codes WHERE email = ?";
        return JdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            VerificationCode vc = new VerificationCode();
            vc.setEmail(email);
            vc.setCode(rs.getString("code"));
            vc.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return vc;
        });
    }
}
