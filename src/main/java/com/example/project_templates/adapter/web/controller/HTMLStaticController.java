package com.example.project_templates.adapter.web.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/static")
public class HTMLStaticController {

    @GetMapping("/html/sample")
    @SneakyThrows
    public ResponseEntity<?> showSample() {
        Path path = Paths.get("src\\main\\resources\\sample\\sample.html").toAbsolutePath();
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new UrlResource(path.toUri()));
    }
}