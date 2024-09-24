package sportpath.models;

import java.time.LocalDateTime;

public class CourtOnline {
    private int courtId;
    private String courtAddress;
    private String courtSport;
    private int entryId;
    private LocalDateTime entryTime;

    public CourtOnline(int courtId, String courtAddress, String courtSport, int entryId, LocalDateTime entryTime) {
        this.courtId = courtId;
        this.courtAddress = courtAddress;
        this.courtSport = courtSport;
        entryId = entryId;
        this.entryTime = entryTime;
    }

    public CourtOnline() {}

    public int getCourtId() {
        return courtId;
    }

    public String getCourtAddress() {
        return courtAddress;
    }

    public String getCourtSport() {
        return courtSport;
    }

    public int getEntryId() {
        return entryId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public void setCourtAddress(String courtAddress) {
        this.courtAddress = courtAddress;
    }

    public void setCourtSport(String courtSport) {
        this.courtSport = courtSport;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}
