package sportpath.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sportpath.ApiResponse;
import sportpath.models.Court;
import sportpath.services.CourtService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("courts")
public class CourtController {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Court>>> getCourtsBySport(@RequestParam("sport") String sport) {
        List<Court> courts = courtService.getAllCourts(sport);
        ApiResponse<List<Court>> response;
        if (courts.isEmpty()) {
            response = new ApiResponse<>(2, "No courts found for the specified sport", null);
        } else {
            response = new ApiResponse<>(3, "Courts retrieved successfully", courts);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/online")
    public ResponseEntity<ApiResponse> getOnlineCourts(@RequestParam ("courtId") int courtId,
                                                                    @RequestParam ("entryTime") LocalDateTime entryTime) {
        int courtOnline = courtService.courtOnline(courtId, entryTime);

        return ResponseEntity.ok(new ApiResponse(200, "Court online retrieved successfully", courtOnline));
    }
}
