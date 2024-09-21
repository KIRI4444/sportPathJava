package sportpath.dao.interfaces;

import sportpath.models.CourtOnline;
import sportpath.models.Entry;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryDAO {
    List<CourtOnline> findUserEntries(int id, LocalDateTime date);
    void saveEntry(Entry entry);
    void deleteEntry(int id);

}
