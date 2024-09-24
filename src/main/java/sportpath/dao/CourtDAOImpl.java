package sportpath.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sportpath.dao.interfaces.CourtDAO;
import sportpath.models.Court;

import java.time.LocalDateTime;
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
        String sql = "select * from courts where sport=?";
        return JdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Court.class), sport);
    }

    @Override
    public int CourtOnline(int courtId, LocalDateTime entryTime) {
        String sql = "select count(*) from entries where courtId=? and entryTime=?";
        return JdbcTemplate.queryForObject(sql, new Object[]{courtId, entryTime}, Integer.class);
    }
}
