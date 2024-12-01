package com.baykov.daniel.lang.repository.mapper;

import com.baykov.daniel.lang.entity.LanguageEntity;
import com.baykov.daniel.lang.payload.dto.response.LanguageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = TranslationMapper.class)
public interface LanguageMapper {

    @Mapping(target = "code", source = "code")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "translations", source = "translations")
    LanguageResponseDto entityToDto(LanguageEntity language);

    List<LanguageResponseDto> languagesToLanguageResponseDtos(List<LanguageEntity> languages);
}
