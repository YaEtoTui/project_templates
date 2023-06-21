package com.example.project_templates.service.factory;

import com.example.project_templates.adapter.repository.GreatQuotesRepository;
import com.example.project_templates.common.exception.NotFoundGreatQuoteException;
import com.example.project_templates.domain.dto.request.CreateGreatQuotesRequest;
import com.example.project_templates.domain.dto.response.GreatQuotesResponse;
import com.example.project_templates.domain.entity.GreatQuotes;
import com.example.project_templates.domain.entity.context.GreatQuotesContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GreatQuotesFactory {
    GreatQuotesRepository greatQuotesRepository;

    public GreatQuotesContext createGreatQuotesContext(CreateGreatQuotesRequest request) {
        return new GreatQuotesContext(
                request.getPerson(),
                request.getPhrase()
        );
    }

    public GreatQuotesResponse createGreatQuotesResponse(GreatQuotes greatQuotes) {
        return new GreatQuotesResponse(
                greatQuotes.getId(),
                greatQuotes.getPerson(),
                greatQuotes.getPhrase()
        );
    }

    public String showGreatQuote(Model model, Long id) {
        GreatQuotes greatQuotes = greatQuotesRepository.findById(id)
                .orElseThrow(() -> new NotFoundGreatQuoteException(
                        String.format("Not found id '%s' great quote", id)
                        )
                );
        model.addAttribute("id", greatQuotes.getId());
        model.addAttribute("person", greatQuotes.getPerson());
        model.addAttribute("phrase", greatQuotes.getPhrase());
        return "great_quote";
    }
}
