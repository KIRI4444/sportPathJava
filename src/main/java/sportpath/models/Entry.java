package sportpath.models;

import java.time.LocalDateTime;

public class Entry {
    private int id;
    private int userId;
    private int courtId;
    private LocalDateTime entryTime;

    public Entry() {}

    public Entry(int id, int userId, int courtId, LocalDateTime entryTime) {
        this.id = id;
        this.userId = userId;
        this.courtId = courtId;
        this.entryTime = entryTime;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}
