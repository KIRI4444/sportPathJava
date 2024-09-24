package sportpath.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportpath.ApiResponse;
import sportpath.models.Court;
import sportpath.models.CourtOnline;
import sportpath.models.Entry;
import sportpath.services.EntryService;

import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/set")
    public ResponseEntity<ApiResponse> setEntry(@RequestBody Entry entry) {
        return entryService.setEntry(entry);
    }

    @GetMapping("/delete")
    public ResponseEntity<ApiResponse> deleteEntry(@RequestParam("id") int id) {
        return entryService.deleteEntry(id);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CourtOnline>>> getAllEntries(@RequestParam("userId") int id) {
        List<CourtOnline> userEntries = entryService.findUserEntries(id);
        ApiResponse<List<CourtOnline>> response;

        if (userEntries.isEmpty()) {
            response = new ApiResponse<>(2, "user doesn`t have entries", null);
        } else {
            response = new ApiResponse<>(3, "user entries retrieved successfully", userEntries);
        }

        return ResponseEntity.ok(response);
    }
}
