package com.example.project_templates.adapter.web.controller;

import com.example.project_templates.domain.dto.request.CreateGreatQuotesRequest;
import com.example.project_templates.domain.dto.response.GreatQuotesResponse;
import com.example.project_templates.service.GreatQuotesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    GreatQuotesService greatQuotesService;

    @PostMapping("/person/phrase")
    public ResponseEntity<GreatQuotesResponse> createPhraseInDB(@RequestBody CreateGreatQuotesRequest request) {
        return ResponseEntity.ok()
                .body(greatQuotesService.createPhrase(request));
    }

    @PostMapping("/person/phrase/image/{id}")
    public ResponseEntity<String> addImageInDB(@PathVariable("id") Long id,
                                               @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok()
                .body(greatQuotesService.addImage(id, image));
    }
}
