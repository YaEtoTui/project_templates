package com.example.project_templates.adapter.web.controller;

import com.example.project_templates.adapter.repository.GreatQuotesRepository;
import com.example.project_templates.common.exception.NotFoundGreatQuoteException;
import com.example.project_templates.domain.dto.request.CreateGreatQuotesRequest;
import com.example.project_templates.domain.dto.response.GreatQuotesResponse;
import com.example.project_templates.domain.entity.GreatQuotes;
import com.example.project_templates.service.GreatQuotesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    GreatQuotesService greatQuotesService;
    GreatQuotesRepository greatQuotesRepository;

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

    @GetMapping("/image/{id}")
    @SneakyThrows
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id) {
        GreatQuotes greatQuotes = greatQuotesRepository.findById(id)
                .orElseThrow(() -> new NotFoundGreatQuoteException(
                        String.format("Not found id '%s' great quote", id)
                )
        );
        Path path = Paths.get(greatQuotes.getPathImage());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new UrlResource(path.toUri()));
    }
}
