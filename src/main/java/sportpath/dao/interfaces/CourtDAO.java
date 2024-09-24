package sportpath.dao.interfaces;

import sportpath.models.Court;

import java.time.LocalDateTime;
import java.util.List;

public interface CourtDAO {
    List<Court> findAllBySport(String sport);
    int CourtOnline(int courtId, LocalDateTime entryTime);
}
