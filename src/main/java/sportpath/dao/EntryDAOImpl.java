package sportpath.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sportpath.dao.interfaces.EntryDAO;
import sportpath.models.CourtOnline;
import sportpath.models.Entry;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntryDAOImpl implements EntryDAO {

    private final JdbcTemplate JdbcTemplate;

    @Autowired
    public EntryDAOImpl(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }


    @Override
    public List<CourtOnline> findUserEntries(int id, LocalDateTime date) {
        String sql = "SELECT courts.id AS courtId, courts.address AS courtAddress, courts.sport AS courtSport, " +
                "entries.entryTime, entries.id AS entryId " +
                "FROM courts INNER JOIN entries on courts.id = entries.courtId " +
                "WHERE entries.userId = ? AND entries.entryTime > ?";
        return JdbcTemplate.query(sql, new Object[]{id, date}, new BeanPropertyRowMapper<>(CourtOnline.class));
    }


    @Override
    public void saveEntry(Entry entry) {
        String sql = "INSERT INTO entries(userid, courtid, entrytime) VALUES (?, ?, ?)";
        JdbcTemplate.update(sql, entry.getUserId(), entry.getCourtId(), entry.getEntryTime());
    }


    @Override
    public void deleteEntry(int id) {
        String sql = "DELETE FROM entries WHERE id = ?";
        JdbcTemplate.update(sql, id);
    }
}
