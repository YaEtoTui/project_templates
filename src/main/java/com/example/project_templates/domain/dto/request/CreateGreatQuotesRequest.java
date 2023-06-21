package com.example.project_templates.domain.dto.request;

import lombok.NonNull;
import lombok.Value;

@Value
public class CreateGreatQuotesRequest {
    @NonNull
    String person;
    @NonNull
    String phrase;
}
