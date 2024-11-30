package com.baykov.daniel.lang.repository.mapper;

import com.baykov.daniel.lang.entity.Translation;
import com.baykov.daniel.lang.payload.dto.response.TranslationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TranslationMapper {

    @Mapping(target = "key", source = "key")
    @Mapping(target = "value", source = "value")
    TranslationResponseDto entityToDto(Translation translation);
}
