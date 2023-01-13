package uz.pdp.task_codingba.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.controller.payload.LanguageDto;
import uz.pdp.task_codingba.entite.Language;
import uz.pdp.task_codingba.repo.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addLanguage(LanguageDto languageDto) {

        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language added", true);
    }

    public List<Language> getLanguages() {

        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }

    public Language getlanguageById(Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Language not found", false);
        }
        Language editingLanguage = optionalLanguage.get();
        editingLanguage.setName(languageDto.getName());
        languageRepository.save(editingLanguage);
        return new ApiResponse("Language edited", true);
    }

    public ApiResponse deleteLanguage(Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Language not found", false);
        }
        languageRepository.deleteById(id);
        return new ApiResponse("Language deleted", true);
    }
}
