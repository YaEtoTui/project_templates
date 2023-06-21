package com.example.project_templates.service.factory;

import com.example.project_templates.adapter.repository.GreatQuotesRepository;
import com.example.project_templates.common.exception.NotFoundGreatQuoteException;
import com.example.project_templates.domain.dto.request.CreateGreatQuotesRequest;
import com.example.project_templates.domain.dto.response.GreatQuotesResponse;
import com.example.project_templates.domain.entity.GreatQuotes;
import com.example.project_templates.domain.entity.context.GreatQuotesContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        model.addAttribute("image", greatQuotes.getPathImage());
        return "great_quote";
    }

    public String showGreatQuotesAll(Model model) {
        List<GreatQuotes> greatQuotesList = greatQuotesRepository.findAll();
        model.addAttribute("list_great_quotes", greatQuotesList);
        return "list_great_quotes";
    }

    @SneakyThrows
    public Path addImageInFileSystem(MultipartFile image) {
        Path path = Paths.get("src\\main\\resources\\image\\" + image.getOriginalFilename()).toAbsolutePath();
        File convertImage = new File(path.toString());

        try (FileOutputStream fileOutputStream = new FileOutputStream(convertImage)) {
            fileOutputStream.write(image.getBytes());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        System.out.println("File has uploaded!");
        return path;
    }

    public String saveInDBPath(Long id, Path pathImage) {
        GreatQuotes greatQuotes = greatQuotesRepository.findById(id)
                .orElseThrow(() -> new NotFoundGreatQuoteException(
                                String.format("Not found id '%s' great quote", id)
                        )
                );

        GreatQuotes greatQuotesEntity = new GreatQuotes(greatQuotes, pathImage);

        greatQuotesRepository.save(greatQuotesEntity);

        return  "Image(Path to image) has saved in db!";
    }
}
