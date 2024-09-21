package sportpath.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sportpath.dao.interfaces.CourtDAO;
import sportpath.models.Court;

import java.util.List;

@Repository
public class CourtDAOImpl implements CourtDAO {
    private final JdbcTemplate JdbcTemplate;

    @Autowired
    public CourtDAOImpl(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }

    @Override
    public List<Court> findAllBySport(String sport) {
        String sql = "select * from court where sport=?";
        return JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Court.class), sport);
    }

    @Override
    public List<Court> CourtOnline(int CourtId, String date) {
        String sql = "select * from court where court_id=? and date=?";
        return JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Court.class), CourtId, date);
    }
}
