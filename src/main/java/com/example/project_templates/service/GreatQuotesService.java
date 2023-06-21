package com.example.project_templates.service;

import com.example.project_templates.domain.dto.request.CreateGreatQuotesRequest;
import com.example.project_templates.domain.dto.response.GreatQuotesResponse;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface GreatQuotesService {

    GreatQuotesResponse createPhrase(CreateGreatQuotesRequest request);

    String showPersonPhrase(Model model, Long id);

    String showPersonPhraseAll(Model model);

    String addImage(Long id, MultipartFile image);
}
