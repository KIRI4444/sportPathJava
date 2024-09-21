package sportpath.dao.interfaces;

import sportpath.models.Court;

import java.util.List;

public interface CourtDAO {
    List<Court> findAllBySport(String sport);
    List<Court> CourtOnline(int id, String date);

}
