package sportpath.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sportpath.dao.CourtDAOImpl;
import sportpath.models.Court;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CourtService {

    private final CourtDAOImpl courtDAOImpl;

    @Autowired
    public CourtService(CourtDAOImpl courtDAOImpl) {
        this.courtDAOImpl = courtDAOImpl;
    }

    public List<Court> getAllCourts(String sport) {
        if (sport == null || sport.trim().isEmpty()) {
            return Collections.emptyList();  // Возвращаем пустой список вместо null
        }
        return courtDAOImpl.findAllBySport(sport);
    }

    public int courtOnline(int courtId, LocalDateTime entryTime) {
        return courtDAOImpl.CourtOnline(courtId, entryTime);
    }
}
