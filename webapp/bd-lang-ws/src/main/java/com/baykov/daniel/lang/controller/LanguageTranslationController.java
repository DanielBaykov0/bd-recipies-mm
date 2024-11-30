package com.baykov.daniel.lang.controller;

import com.baykov.daniel.lang.payload.dto.response.LanguageResponseDto;
import com.baykov.daniel.lang.payload.dto.response.TranslationResponseDto;
import com.baykov.daniel.lang.service.LanguageTranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LanguageTranslationController {

    private final LanguageTranslationService languageTranslationService;

    @GetMapping("/lang")
    public ResponseEntity<List<LanguageResponseDto>> getAllLanguageTranslations() {
        List<LanguageResponseDto> response = languageTranslationService.getAllLanguageTranslations();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lang/{code}")
    public ResponseEntity<List<TranslationResponseDto>> getTranslationsByLanguage(@PathVariable("code") String languageCode) {
        List<TranslationResponseDto> response = languageTranslationService.getTranslationsByLanguage(languageCode);
        return ResponseEntity.ok(response);
    }
}
