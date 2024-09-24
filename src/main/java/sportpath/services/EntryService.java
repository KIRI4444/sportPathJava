package sportpath.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sportpath.ApiResponse;
import sportpath.dao.EntryDAOImpl;
import sportpath.models.CourtOnline;
import sportpath.models.Entry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService {

    private final EntryDAOImpl entryDAOImpl;

    @Autowired
    public EntryService(EntryDAOImpl entryDAOImpl) {
        this.entryDAOImpl = entryDAOImpl;
    }

    public ResponseEntity<ApiResponse> setEntry(Entry entry) {
        //Проверка, нет ли уже такой встречи/нет ли другой встречи на это время
        // 1 - победа
        // 2 - уже есть встреча

        int userId = entry.getUserId();
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();

        if (!checkEntries(entry)) {
            return ResponseEntity.ok(new ApiResponse(2, "entry on this time already exists", ""));
        }

        try {
            entryDAOImpl.saveEntry(entry);
            return ResponseEntity.ok(new ApiResponse(1, "entry set successfully", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(2, "entry set failed", ""));
        }
    }

    public boolean checkEntries(Entry entry) {
        //Проверка, нет ли уже такой встречи/нет ли другой встречи на это время
        int userId = entry.getUserId();
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        List<CourtOnline> userEntries = entryDAOImpl.findUserEntries(userId, currentDateTime);

        LocalDateTime newEntryStartTime = entry.getEntryTime();

        for (CourtOnline existingEntry : userEntries) {
            LocalDateTime existingEntryStartTime = existingEntry.getEntryTime();

            if (newEntryStartTime.equals(existingEntryStartTime)) {
                return false;
            }
        }

        return true;
    }

    public ResponseEntity<ApiResponse> deleteEntry(int entryId) {
        try {
            entryDAOImpl.deleteEntry(entryId);
            return ResponseEntity.ok(new ApiResponse(1, "entry deleted successfully", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(2, "entry could not be deleted", ""));
        }
    }

    public List<CourtOnline> findUserEntries(int userId) {
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        return entryDAOImpl.findUserEntries(userId, currentDateTime);
    }
}
