package com.baykov.daniel.lang.service;


import com.baykov.daniel.lang.entity.Language;
import com.baykov.daniel.lang.entity.Translation;
import com.baykov.daniel.lang.payload.dto.response.LanguageResponseDto;
import com.baykov.daniel.lang.payload.dto.response.TranslationResponseDto;
import com.baykov.daniel.lang.repository.LanguageRepository;
import com.baykov.daniel.lang.repository.TranslationRepository;
import com.baykov.daniel.lang.repository.mapper.LanguageMapper;
import com.baykov.daniel.lang.repository.mapper.TranslationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageTranslationService {

    private final LanguageRepository languageRepository;
    private final TranslationRepository translationRepository;
    private final LanguageMapper languageMapper;
    private final TranslationMapper translationMapper;

    public List<LanguageResponseDto> getAllLanguageTranslations() {
        List<Language> languages = languageRepository.findAll();
        return languageMapper.languagesToLanguageResponseDtos(languages);
    }

    public List<TranslationResponseDto> getTranslationsByLanguage(String languageCode) {
        List<Translation> translations = translationRepository.findByIdCode(languageCode)
                .orElseThrow(() -> new EntityNotFoundException("No translations found for language code: " + languageCode));
        return translationMapper.entityToDto(translations);
    }
}
