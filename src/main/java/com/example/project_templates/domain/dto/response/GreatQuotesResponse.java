package com.example.project_templates.domain.dto.response;

import lombok.Value;

@Value
public class GreatQuotesResponse {
    Long id;
    String person;
    String phrase;
}
