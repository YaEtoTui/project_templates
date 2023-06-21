package com.example.project_templates.service.impl;

import com.example.project_templates.adapter.repository.GreatQuotesRepository;
import com.example.project_templates.domain.dto.request.CreateGreatQuotesRequest;
import com.example.project_templates.domain.dto.response.GreatQuotesResponse;
import com.example.project_templates.domain.entity.GreatQuotes;
import com.example.project_templates.domain.entity.context.GreatQuotesContext;
import com.example.project_templates.service.GreatQuotesService;
import com.example.project_templates.service.factory.GreatQuotesFactory;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class GreatQuotesServiceImpl implements GreatQuotesService {
    GreatQuotesFactory greatQuotesFactory;
    GreatQuotesRepository greatQuotesRepository;

    @Override
    public GreatQuotesResponse createPhrase(CreateGreatQuotesRequest request) {
        GreatQuotesContext context = greatQuotesFactory.createGreatQuotesContext(request);
        GreatQuotes greatQuotes = new GreatQuotes(context);
        GreatQuotes greatQuotesEntity = greatQuotesRepository.save(greatQuotes);
        return greatQuotesFactory.createGreatQuotesResponse(greatQuotesEntity);
    }

    @Override
    public String showPersonPhrase(Model model, Long id) {
        return greatQuotesFactory.showGreatQuote(model, id);
    }
}