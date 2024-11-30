package com.baykov.daniel.lang.service;


import com.baykov.daniel.lang.entity.Language;
import com.baykov.daniel.lang.payload.dto.response.LanguageResponseDto;
import com.baykov.daniel.lang.repository.LanguageRepository;
import com.baykov.daniel.lang.repository.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageTranslationService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public List<LanguageResponseDto> getAllLanguageTranslations() {
        List<Language> languages = languageRepository.findAll();
        return languageMapper.languagesToLanguageResponseDtos(languages);
    }
}
