package com.example.project_templates.adapter.web.controller;

import com.example.project_templates.service.GreatQuotesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api")
public class SampleMVCController {
    GreatQuotesService greatQuotesService;

    @GetMapping("/sample/phrase_1")
    public String showSamplePhrase1(Model model) {
        model.addAttribute("title", "Первая цитата");
        model.addAttribute("phrase", "Хорош");
        return "sample_1";
    }

    @GetMapping("/sample/phrase_2")
    public String showSamplePhrase2(Model model) {
        model.addAttribute("title", "Вторая цитата");
        model.addAttribute("phrase", "Я никого не душню");
        return "sample_1";
    }

    @GetMapping("/sample/person/phrase/{id}")
    public String showPersonPhrase(Model model, @PathVariable("id") long id) {
        return greatQuotesService.showPersonPhrase(model, id);
    }

    @GetMapping("/sample/person/phrase/all")
    public String showPersonPhraseAll(Model model) {
        return greatQuotesService.showPersonPhraseAll(model);
    }
}
