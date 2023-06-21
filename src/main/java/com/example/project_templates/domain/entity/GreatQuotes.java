package com.example.project_templates.domain.entity;

import com.example.project_templates.domain.entity.context.GreatQuotesContext;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "greate_quotes")
public class GreatQuotes extends BaseDomainEntity{
    String person;
    String phrase;
    String pathImage;

    public GreatQuotes(GreatQuotesContext context) {
        person = context.getPerson();
        phrase = context.getPhrase();
    }

    public GreatQuotes(GreatQuotes greatQuotes, Path path) {
        id = greatQuotes.getId();
        person = greatQuotes.getPerson();
        phrase = greatQuotes.getPhrase();
        pathImage = path.toString();
    }
}
